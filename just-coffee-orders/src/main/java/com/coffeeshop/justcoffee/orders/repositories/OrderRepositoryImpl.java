package com.coffeeshop.justcoffee.orders.repositories;

import com.coffeeshop.justcoffee.orders.models.Order;
import com.coffeeshop.justcoffee.orders.models.OrderItem;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.coffeeshop.justcoffee.orders.utils.IdGenerator.generateId;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final String KEY = "ORDER";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations orderHashOperations;

    public OrderRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        orderHashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Collection<Order> findAllOrders() {
        return (Collection<Order>) orderHashOperations.entries(KEY).values();
    }

    @Override
    public long createOrder(String username, OrderItem[] orderItems) {
        Order order = new Order();
        long generatedId = generateId();
        order.setId(generatedId);
        order.setNow(LocalDateTime.now());
        order.setOrderItems(Arrays.asList(orderItems));
        order.setUsername(username);
        order.setTotalPrice(calculatePrice(orderItems));
        order.setOrderState(OrderState.SUBMITTED);
        orderHashOperations.put(KEY, generatedId, order);
        return generatedId;
    }

    @Override
    public Order getOrderById(long orderId) {
        return (Order) orderHashOperations.get(KEY, orderId);
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return findAllOrders().stream()
                .filter(order -> Objects.equals(order.getUsername(), username))
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrder(Order order) {
        orderHashOperations.delete(KEY, order.getId());
        orderHashOperations.put(KEY, order.getId(), order);
    }

    @Override
    public void deleteById(long orderId) {
        orderHashOperations.delete(KEY, orderId);
    }

    @Override
    public void deleteAll() {
        orderHashOperations.keys(KEY).stream().forEach(k -> orderHashOperations.delete(KEY, k));
    }

    private double calculatePrice(OrderItem[] orderItems) {
        double price = 0;
        for (OrderItem orderItem : orderItems) {
            price += orderItem.getPrice();
        }
        return price;
    }
}

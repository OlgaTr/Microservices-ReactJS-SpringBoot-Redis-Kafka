package com.coffeshop.justcoffee.repositories.implementations;

import com.coffeshop.justcoffee.models.CoffeeDrink;
import com.coffeshop.justcoffee.models.Order;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeDrinkRepository;
import com.coffeshop.justcoffee.repositories.interfaces.OrderRepository;
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

import static com.coffeshop.justcoffee.utils.IdGenerator.generateId;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final String KEY = "ORDER";
    private final RedisTemplate<String, Object> redisTemplate;
    private final CoffeeDrinkRepository coffeeDrinkRepository;
    private HashOperations orderHashOperations;

    public OrderRepositoryImpl(RedisTemplate<String, Object> redisTemplate, CoffeeDrinkRepository coffeeDrinkRepository) {
        this.redisTemplate = redisTemplate;
        this.coffeeDrinkRepository = coffeeDrinkRepository;
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
    public long createOrder(String username, Long[] coffeeDrinksId) {
        Order order = new Order();
        long generatedId = generateId();
        order.setId(generatedId);
        order.setNow(LocalDateTime.now());
        order.setCoffeeDrinks(Arrays.asList(coffeeDrinksId));
        order.setUsername(username);
        orderHashOperations.put(KEY, generatedId, order);
        return generatedId;
    }

    @Override
    public Order getOrderById(long orderId) {
        return (Order) orderHashOperations.get(KEY, orderId);
    }

    @Override
    public Order getOrderByUsername(String username) {
        return findAllOrders().stream()
                .filter(order -> Objects.equals(order.getUsername(), username))
                .findFirst()
                .get();
    }

    @Override
    public void updateOrder(Order order) {
        orderHashOperations.delete(KEY, order.getId());
        orderHashOperations.put(KEY, order.getId(), order);
    }

//    @Override
//    public void addCoffeeDrinkToOrder(long orderId, long coffeeDrinkId) {
//        Order order = (Order) orderHashOperations.get(KEY, orderId);
//        order.addCoffeeDrink(coffeeDrinkId);
//        updateOrder(order);
//    }

    @Override
    public List<CoffeeDrink> getCoffeeDrinksByOrderId(long orderId) {
        Order order = (Order) orderHashOperations.get(KEY, orderId);
        List<Long> coffeeOrdersId = order.getCoffeeDrinks();
        return coffeeOrdersId.stream()
                .map(id -> coffeeDrinkRepository.getCoffeeOrderById(id))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long orderId) {
        orderHashOperations.delete(KEY, orderId);
    }

    @Override
    public void deleteAll() {
        orderHashOperations.keys(KEY).stream().forEach(k -> orderHashOperations.delete(KEY, k));
    }
}

package com.coffeshop.justcoffee.repositories;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Random;

@Repository
public class CoffeeOrderRepositoryImpl implements CoffeeOrderRepository {
    private static final String KEY = "COFFEE_ORDER";
    private final RedisTemplate<String, CoffeeOrder> coffeeOrderTemplate;
    private HashOperations coffeeOrderHashOperations;

    public CoffeeOrderRepositoryImpl(RedisTemplate<String, CoffeeOrder> orderTemplate) {
        this.coffeeOrderTemplate = orderTemplate;
    }

    @PostConstruct
    private void init() {
        coffeeOrderHashOperations = coffeeOrderTemplate.opsForHash();
    }

    @Override
    public Collection<CoffeeOrder> findAllOrders() {
        return (Collection<CoffeeOrder>) coffeeOrderHashOperations.entries(KEY).values();
    }

    @Override
    public long addCoffeeOrder() {
        CoffeeOrder coffeeOrder = new CoffeeOrder();
        long generatedId = generateId();
        coffeeOrder.setId(generatedId);
        coffeeOrderHashOperations.put(KEY, generatedId, coffeeOrder);
        return generatedId;
    }

    @Override
    public void findCoffeeOrder(long id) {

    }

    @Override
    public long addCoffeeToCoffeeOrder(long coffeeOrderId, long coffeeId) {
        CoffeeOrder coffeeOrder = (CoffeeOrder) coffeeOrderHashOperations.get(KEY, coffeeOrderId);
        coffeeOrder.setCoffeeId(coffeeId);
        coffeeOrderHashOperations.delete(KEY, coffeeOrderId);
        coffeeOrderHashOperations.put(KEY, coffeeOrderId, coffeeOrder);
        return coffeeId;
    }

    @Override
    public long addToppingToCoffee(long coffeeOrderId, long toppingId) {
        CoffeeOrder coffeeOrder = (CoffeeOrder) coffeeOrderHashOperations.get(KEY, coffeeOrderId);
        coffeeOrder.addToppingToCoffee(toppingId);
        coffeeOrderHashOperations.delete(KEY, coffeeOrderId);
        coffeeOrderHashOperations.put(KEY, coffeeOrderId, coffeeOrder);
        return toppingId;
    }

    @Override
    public void deleteAll() {
        coffeeOrderHashOperations.keys(KEY).stream().forEach(k -> coffeeOrderHashOperations.delete(KEY, k));
    }

    @Override
    public void deleteById(long id) {
        coffeeOrderHashOperations.delete(KEY, id);
    }

    private long generateId() {
        return new Random().nextLong(0, 10000000000000l);
    }
}

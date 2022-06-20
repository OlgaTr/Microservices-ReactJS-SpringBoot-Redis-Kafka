package com.coffeeshop.justcoffee.orders.repositories;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.coffeeshop.justcoffee.orders.utils.IdGenerator.generateId;

@Repository
public class CustomCoffeeRepositoryImpl implements CustomCoffeeRepository {
    private static final String KEY = "COFFEE_ORDER";
    private final RedisTemplate<String, Object> coffeeOrderTemplate;
    private HashOperations coffeeOrderHashOperations;

    public CustomCoffeeRepositoryImpl(RedisTemplate<String, Object> coffeeOrderTemplate) {
        this.coffeeOrderTemplate = coffeeOrderTemplate;
    }

    @PostConstruct
    private void init() {
        coffeeOrderHashOperations = coffeeOrderTemplate.opsForHash();
    }

    @Override
    public Collection<CustomCoffee> findAllCustomCoffee() {
        return (Collection<CustomCoffee>) coffeeOrderHashOperations.entries(KEY).values();
    }

    @Override
    public long createCustomCoffee(CustomCoffee customCoffee) {
        long generatedId = generateId();
        customCoffee.setId(generatedId);
        coffeeOrderHashOperations.put(KEY, generatedId, customCoffee);
        return generatedId;
    }

    @Override
    public CustomCoffee getCoffeeOrderById(long coffeeOrderId) {
        return (CustomCoffee) coffeeOrderHashOperations.get(KEY, coffeeOrderId);
    }

    @Override
    public List<CustomCoffee> getCustomCoffeesById(Long[] coffeeDrinksId) {
        return Arrays.stream(coffeeDrinksId)
                .map(id -> (CustomCoffee) coffeeOrderHashOperations.get(KEY, id))
                .collect(Collectors.toList());
    }

    @Override
    public CustomCoffee updateCoffeeOrder(CustomCoffee customCoffee) {
        long coffeeOrderId = customCoffee.getId();
        coffeeOrderHashOperations.delete(KEY, coffeeOrderId);
        coffeeOrderHashOperations.put(KEY, coffeeOrderId, customCoffee);
        return null;
    }

    @Override
    public void deleteAll() {
        coffeeOrderHashOperations.keys(KEY).stream().forEach(k -> coffeeOrderHashOperations.delete(KEY, k));
    }

    @Override
    public void deleteById(long id) {
        coffeeOrderHashOperations.delete(KEY, id);
    }
}

package com.coffeshop.justcoffee.repositories;

import com.coffeshop.justcoffee.models.Coffee;
import com.coffeshop.justcoffee.models.Topping;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Random;

@Repository
public class CoffeeRepositoryImpl implements CoffeeRepository {
    private static final String KEY = "COFFEE";
    private final RedisTemplate<String, Coffee> coffeeTemplate;
    private HashOperations coffeeHashOperations;

    public CoffeeRepositoryImpl(RedisTemplate<String, Coffee> coffeeTemplate) {
        this.coffeeTemplate = coffeeTemplate;
    }

    @PostConstruct
    private void init() {
        coffeeHashOperations = coffeeTemplate.opsForHash();
    }

    @Override
    public Collection<Coffee> findAllCoffee() {
        return (Collection<Coffee>) coffeeHashOperations.entries(KEY).values();
    }

    @Override
    public void add(Coffee coffee) {
        long generatedId = generateId();
        coffee.setId(generatedId);
        coffeeHashOperations.put(KEY, generatedId, coffee);
    }

    @Override
    public void deleteAll() {
        coffeeHashOperations.keys(KEY).stream().forEach(k -> coffeeHashOperations.delete(KEY, k));
    }

    @Override
    public void deleteById(long id) {
        coffeeHashOperations.delete(KEY, id);
    }

    @Override
    public void deleteByName(String description) {
        coffeeHashOperations.delete(KEY, description);
    }

    @Override
    public Coffee findCoffee(long id) {
        return (Coffee) coffeeHashOperations.get(KEY, id);
    }

    private long generateId() {
        return new Random().nextLong(0, 10000000000000l);
    }
}

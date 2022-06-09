package com.coffeshop.justcoffee.repositories.implementations;

import com.coffeshop.justcoffee.models.Coffee;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static com.coffeshop.justcoffee.utils.IdGenerator.generateId;

@Repository
public class CoffeeRepositoryImpl implements CoffeeRepository {
    private static final String KEY = "COFFEE";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations coffeeHashOperations;

    public CoffeeRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        coffeeHashOperations = redisTemplate.opsForHash();
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
}

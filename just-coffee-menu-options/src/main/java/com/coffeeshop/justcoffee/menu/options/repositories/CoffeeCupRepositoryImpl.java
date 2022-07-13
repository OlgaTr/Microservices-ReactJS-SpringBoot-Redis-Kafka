package com.coffeeshop.justcoffee.menu.options.repositories;

import com.coffeeshop.justcoffee.menu.options.models.CoffeeCup;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static com.coffeeshop.justcoffee.menu.options.utils.IdGenerator.generateId;

@Repository
public class CoffeeCupRepositoryImpl implements CoffeeCupRepository{
    private static final String KEY = "COFFEE_CUP";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations coffeeCupHashOperations;

    public CoffeeCupRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        coffeeCupHashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Collection<CoffeeCup> findAll() {
        return (Collection<CoffeeCup>) coffeeCupHashOperations.entries(KEY).values();
    }

    @Override
    public void add(CoffeeCup coffeeCup) {
        long generatedId = generateId();
        coffeeCup.setId(generatedId);
        coffeeCupHashOperations.put(KEY, generatedId, coffeeCup);
    }

    @Override
    public void deleteAll() {
        coffeeCupHashOperations.keys(KEY).stream().forEach(k -> coffeeCupHashOperations.delete(KEY, k));
    }
}

package com.coffeshop.justcoffee.repositories;

import com.coffeshop.justcoffee.models.Coffee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Random;

@Repository
public class CoffeeRepositoryImpl implements CoffeeRepository {
    private static final String KEY = "COFFEE";
//    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Coffee> redisTemplate;
    private HashOperations hashOperations;

    public CoffeeRepositoryImpl(RedisTemplate<String, Coffee> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    public CoffeeRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Collection findAllCoffee() {
        System.out.println(hashOperations.keys(KEY));
        Collection values = hashOperations.entries(KEY).values();
        return values;
    }

    @Override
    public void add(Coffee coffee) {
        long generatedId = generateId();
        coffee.setId(generatedId);
        hashOperations.put(KEY, generatedId, coffee);
    }

    @Override
    public void deleteAll() {
        hashOperations.keys(KEY).stream().forEach(k -> hashOperations.delete(KEY, k));
    }

    @Override
    public void deleteById(long id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public void deleteByName(String description) {
        hashOperations.delete(KEY, description);
    }

    @Override
    public Coffee findCoffee(long id) {
        return null;
    }

    private long generateId() {
        long tmp = new Random().nextLong();
        return Math.max(tmp, tmp * -1);
    }
}

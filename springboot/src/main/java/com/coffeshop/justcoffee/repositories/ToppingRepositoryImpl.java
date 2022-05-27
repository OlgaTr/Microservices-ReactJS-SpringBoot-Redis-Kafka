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
public class ToppingRepositoryImpl implements ToppingRepository {
    private static final String KEY = "TOPPING";
    private final RedisTemplate<String, Topping> toppingTemplate;
    private HashOperations toppingOperations;

    public ToppingRepositoryImpl(RedisTemplate<String, Topping> toppingTemplate) {
        this.toppingTemplate = toppingTemplate;
    }

    @PostConstruct
    private void init() {
        toppingOperations = toppingTemplate.opsForHash();
    }

    @Override
    public Collection<Topping> findAllToppings() {
        return (Collection<Topping>) toppingOperations.entries(KEY).values();
    }

    @Override
    public void add(Topping topping) {
        long generatedId = generateId();
        topping.setId(generatedId);
        toppingOperations.put(KEY, generatedId, topping);
    }

    @Override
    public Topping findToppingById(long id) {
        return (Topping) toppingOperations.get(KEY, id);
    }

    @Override
    public void deleteAll() {
        toppingOperations.keys(KEY).stream().forEach(k -> toppingOperations.delete(KEY, k));
    }

    @Override
    public void deleteById(long id) {
        toppingOperations.delete(KEY, id);
    }

    private long generateId() {
        return new Random().nextLong(0, 10000000000000l);
    }
}

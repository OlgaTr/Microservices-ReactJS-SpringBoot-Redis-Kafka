package com.coffeshop.justcoffee.repositories.implementations;

import com.coffeshop.justcoffee.models.Topping;
import com.coffeshop.justcoffee.repositories.interfaces.ToppingRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static com.coffeshop.justcoffee.utils.IdGenerator.generateId;

@Repository
public class ToppingRepositoryImpl implements ToppingRepository {
    private static final String KEY = "TOPPING";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations toppingOperations;

    public ToppingRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        toppingOperations = redisTemplate.opsForHash();
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
}

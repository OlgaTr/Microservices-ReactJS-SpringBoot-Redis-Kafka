package com.coffeeshop.justcoffee.customers.repositories;

import com.coffeeshop.justcoffee.customers.models.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String KEY = "USER";
    private final RedisTemplate<String, Object> redisTemplate;
    private final OrderRepository orderRepository;
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate, OrderRepository orderRepository) {
        this.redisTemplate = redisTemplate;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Collection<User> findAllUsers() {
        return hashOperations.entries(KEY).values();
    }

    @Override
    public void addUser(User user) {
        hashOperations.put(KEY, user.getUsername(), user);
    }

    @Override
    public void deleteById(String username) {
        hashOperations.delete(KEY, username);
    }

    @Override
    public void deleteAll() {
        hashOperations.keys(KEY).stream().forEach(k -> hashOperations.delete(KEY, k));
    }

    @Override
    public User findByName(String username) {
        Collection<User> users = hashOperations.entries(KEY).values();
        return users.stream().filter(user -> Objects.equals(user.getUsername(), username)).findFirst().get();
    }

    @Override
    public boolean authenticateUser(User user) {
        Collection<User> users = hashOperations.entries(KEY).values();
        Optional<User> optionalUser = users.stream().filter(u -> Objects.equals(user.getUsername(), u.getUsername())).findFirst();
        return optionalUser.filter(value -> Objects.equals(user.getPassword(), value.getPassword())).isPresent();
    }
}

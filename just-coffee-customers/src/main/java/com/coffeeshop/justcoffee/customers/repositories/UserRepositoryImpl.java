package com.coffeeshop.justcoffee.customers.repositories;

import com.coffeeshop.justcoffee.customers.models.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String KEY = "USER";
    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }
//
//    @PostConstruct
//    private void init() {
//        hashOperations = redisTemplate.opsForHash();
//    }

    @Override
    public Collection<User> findAllUsers() {
        return hashOperations.entries(KEY).values();
    }

    @Override
    public void addUser(User user) {
        hashOperations.put(KEY, user.getUsername(), user);
    }

    @Override
    public void deleteByUsername(String username) {
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
        Optional<User> optionalUser = users.stream().filter(u -> user.getUsername().equals(u.getUsername())).findFirst();
        boolean isAuth = optionalUser.filter(value -> user.getPassword().equals(value.getPassword())).isPresent();
        return isAuth;
    }
}

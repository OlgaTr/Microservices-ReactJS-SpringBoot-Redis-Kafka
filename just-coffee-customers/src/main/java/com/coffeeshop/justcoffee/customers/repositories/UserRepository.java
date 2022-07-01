package com.coffeeshop.justcoffee.customers.repositories;

import com.coffeeshop.justcoffee.customers.models.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findAllUsers();
    void addUser(User user);
    User findByName(String name);
    boolean authenticateUser(User user);
    void deleteByUsername(String name);
    void deleteAll();
}

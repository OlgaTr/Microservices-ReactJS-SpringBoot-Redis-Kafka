package com.coffeshop.justcoffee.repositories.interfaces;

import com.coffeshop.justcoffee.models.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findAllUsers();
    void addUser(User user);
    User findByName(String name);
    boolean authenticateUser(User user);
    void deleteById(String name);
    void deleteAll();
}

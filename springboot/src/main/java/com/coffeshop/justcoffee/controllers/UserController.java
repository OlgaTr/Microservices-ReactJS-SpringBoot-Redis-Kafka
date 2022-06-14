package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.User;
import com.coffeshop.justcoffee.repositories.interfaces.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        userRepository.addUser(user);
    }

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findAllUsers();
    }

    @PostMapping("/users/signIn")
    public boolean authenticateUser(@RequestBody User user) {
        return userRepository.authenticateUser(user);
    }

    @DeleteMapping("/users")
    public void deleteAll() {
        userRepository.deleteAll();
    }
}

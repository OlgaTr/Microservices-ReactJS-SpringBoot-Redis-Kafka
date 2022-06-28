package com.coffeeshop.justcoffee.customers.controllers;

import com.coffeeshop.justcoffee.customers.models.User;
import com.coffeeshop.justcoffee.customers.repositories.UserRepository;
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

//    @PostMapping("/users/authenticate")
//    public void authenticateAndRedirect() {
//        System.out.println("I am customers service");
//    }
}

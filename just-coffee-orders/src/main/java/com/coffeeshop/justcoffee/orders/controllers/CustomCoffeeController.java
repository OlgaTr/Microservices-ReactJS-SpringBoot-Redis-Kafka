package com.coffeeshop.justcoffee.orders.controllers;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;
import com.coffeeshop.justcoffee.orders.repositories.CustomCoffeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomCoffeeController {
    private final CustomCoffeeRepository customCoffeeRepository;

    public CustomCoffeeController(CustomCoffeeRepository customCoffeeRepository) {
        this.customCoffeeRepository = customCoffeeRepository;
    }

    @GetMapping("/coffeeOrders")
    public Collection<CustomCoffee> getCoffeeOrders() {
        return customCoffeeRepository.findAllCustomCoffee();
    }

    @GetMapping(value = "/coffeeOrders/{coffeeOrderId}")
    public CustomCoffee getCoffeeOrderById(@PathVariable long coffeeOrderId) {
        return customCoffeeRepository.getCoffeeOrderById(coffeeOrderId);
    }

    @PostMapping(value = "/coffeeDrinks")
    public List<CustomCoffee> getCoffeeDrinksById(@RequestBody Long[] coffeeDrinksId) {
        return customCoffeeRepository.getCustomCoffeesById(coffeeDrinksId);
    }

    @DeleteMapping("/coffeeOrders")
    public void deleteAll() {
        customCoffeeRepository.deleteAll();
    }

    @DeleteMapping("/coffeeOrders/{id}")
    public void deleteById(@PathVariable long id) {
        customCoffeeRepository.deleteById(id);
    }
}

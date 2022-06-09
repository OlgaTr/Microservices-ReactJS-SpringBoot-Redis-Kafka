package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.CoffeeDrink;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeDrinkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CoffeeDrinkController {
    private final CoffeeDrinkRepository coffeeDrinkRepository;

    public CoffeeDrinkController(CoffeeDrinkRepository coffeeDrinkRepository) {
        this.coffeeDrinkRepository = coffeeDrinkRepository;
    }

    @PostMapping("/coffeeOrders/{coffeeId}")
    public long createCoffeeOrder(@PathVariable long coffeeId, @RequestBody Long[] toppingsId) {
        return coffeeDrinkRepository.createCoffeeOrder(coffeeId, toppingsId);
    }

    @GetMapping("/coffeeOrders")
    public Collection<CoffeeDrink> getCoffeeOrders() {
        return coffeeDrinkRepository.findAllCoffeeOrders();
    }

    @GetMapping(value = "/coffeeOrders/{coffeeOrderId}")
    public CoffeeDrink getCoffeeOrderById(@PathVariable long coffeeOrderId) {
        return coffeeDrinkRepository.getCoffeeOrderById(coffeeOrderId);
    }

    @PostMapping(value = "/coffeeDrinks")
    public List<CoffeeDrink> getCoffeeDrinksById(@RequestBody Long[] coffeeDrinksId) {
        return coffeeDrinkRepository.getCoffeeDrinksById(coffeeDrinksId);
    }

    @DeleteMapping("/coffeeOrders")
    public void deleteAll() {
        coffeeDrinkRepository.deleteAll();
    }

    @DeleteMapping("/coffeeOrders/{id}")
    public void deleteById(@PathVariable long id) {
        coffeeDrinkRepository.deleteById(id);
    }
}

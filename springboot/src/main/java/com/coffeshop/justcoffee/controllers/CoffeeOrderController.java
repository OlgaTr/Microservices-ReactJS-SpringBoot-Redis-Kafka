package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeOrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CoffeeOrderController {
    private final CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrderController(CoffeeOrderRepository coffeeOrderRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
    }

    @PostMapping("/coffeeOrders/{coffeeId}")
    public long createCoffeeOrder(@PathVariable long coffeeId, @RequestBody Long[] toppingsId) {
        return coffeeOrderRepository.createCoffeeOrder(coffeeId, toppingsId);
    }

    @GetMapping("/coffeeOrders")
    public Collection<CoffeeOrder> getCoffeeOrders() {
        return coffeeOrderRepository.findAllCoffeeOrders();
    }

    @GetMapping("/coffeeOrders/{coffeeOrderId}")
    public CoffeeOrder getCoffeeOrderById(@PathVariable long coffeeOrderId) {
        return coffeeOrderRepository.getCoffeeOrderById(coffeeOrderId);
    }

    @PostMapping("/coffeeDrinks")
    public List<CoffeeOrder> getCoffeeDrinksById(@RequestBody Long[] coffeeDrinksId) {
        return coffeeOrderRepository.getCoffeeDrinksById(coffeeDrinksId);
    }

    @DeleteMapping("/coffeeOrders")
    public void deleteAll() {
        coffeeOrderRepository.deleteAll();
    }

    @DeleteMapping("/coffeeOrders/{id}")
    public void deleteById(@PathVariable long id) {
        coffeeOrderRepository.deleteById(id);
    }
}

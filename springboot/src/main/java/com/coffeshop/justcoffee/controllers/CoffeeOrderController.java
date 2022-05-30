package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeOrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class CoffeeOrderController {
    private final CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrderController(CoffeeOrderRepository coffeeOrderRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
    }

    @PostMapping("/coffeeOrders")
    public long createCoffeeOrder() {
        return coffeeOrderRepository.addCoffeeOrder();
    }

    @GetMapping("/coffeeOrders")
    public Collection<CoffeeOrder> getCoffeeOrders() {
        return coffeeOrderRepository.findAllCoffeeOrders();
    }

    @GetMapping("/coffeeOrders/{coffeeOrderId}")
    public CoffeeOrder getCoffeeOrderById(@PathVariable long coffeeOrderId) {
        return coffeeOrderRepository.getCoffeeOrderById(coffeeOrderId);
    }

    @PutMapping("/coffeeOrders/{coffeeOrderId}/{coffeeId}")
    public long addCoffeeToCoffeeOrder(@PathVariable long coffeeOrderId, @PathVariable long coffeeId) {
        return coffeeOrderRepository.addCoffeeToCoffeeOrder(coffeeOrderId, coffeeId);
    }

    @PutMapping("/coffeeOrders/{coffeeOrderId}")
    public void addToppingsToCoffee(@PathVariable long coffeeOrderId, @RequestBody Long[] toppingsId) {
        coffeeOrderRepository.addToppingsToCoffee(coffeeOrderId, toppingsId);
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

package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.repositories.CoffeeOrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class CoffeeOrderController {
    private final CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrderController(CoffeeOrderRepository coffeeOrderRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
    }

    @PostMapping("/orders")
    public long createCoffeeOrder() {
        return coffeeOrderRepository.addCoffeeOrder();
    }

    @GetMapping("/orders")
    public Collection<CoffeeOrder> getOrders() {
        return coffeeOrderRepository.findAllOrders();
    }

    @PutMapping("/orders/{coffeeOrderId}/{coffeeId}")
    public long addCoffeeToCoffeeOrder(@PathVariable long coffeeOrderId, @PathVariable long coffeeId) {
        return coffeeOrderRepository.addCoffeeToCoffeeOrder(coffeeOrderId, coffeeId);
    }

    @PutMapping("/coffeeOrders/{coffeeOrderId}/{toppingId}")
    public void addToppingToCoffee(@PathVariable long coffeeOrderId, @PathVariable long toppingId) {
        coffeeOrderRepository.addToppingToCoffee(coffeeOrderId, toppingId);
    }

    @DeleteMapping("/orders")
    public void deleteAll() {
        coffeeOrderRepository.deleteAll();
    }

    @DeleteMapping("/orders/{id}")
    public void deleteById(@PathVariable long id) {
        coffeeOrderRepository.deleteById(id);
    }
}

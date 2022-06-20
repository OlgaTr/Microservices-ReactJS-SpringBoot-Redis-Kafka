package com.coffeeshop.justcoffee.menu.options.controllers;

import com.coffeeshop.justcoffee.menu.options.models.Coffee;
import com.coffeeshop.justcoffee.menu.options.repositories.CoffeeRepository;
import com.coffeeshop.justcoffee.menu.options.kafka.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;
    private final MessageService messageService;

    public CoffeeController(CoffeeRepository coffeeRepository, MessageService messageService) {
        this.coffeeRepository = coffeeRepository;
        this.messageService = messageService;
    }

    @PostMapping("/coffee")
    public void addCoffee(@RequestBody Coffee coffee) {
        coffeeRepository.add(coffee);
    }

    @GetMapping("/coffee")
    public Collection<Coffee> getCoffee() {
        return coffeeRepository.findAllCoffee();
    }

    @GetMapping("/coffee/{id}")
    public Coffee getCoffeeById(@PathVariable long id) {
        return coffeeRepository.findCoffee(id);
    }

    @DeleteMapping("/coffee")
    public void deleteAll() {
        coffeeRepository.deleteAll();
    }

    @DeleteMapping("/coffee/{id}")
    public void deleteById(@PathVariable long id) {
        coffeeRepository.deleteById(id);
    }
}

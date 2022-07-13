package com.coffeeshop.justcoffee.menu.options.controllers;

import com.coffeeshop.justcoffee.menu.options.models.CoffeeCup;
import com.coffeeshop.justcoffee.menu.options.repositories.CoffeeCupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class CoffeeCupController {
    private final CoffeeCupRepository coffeeCupRepository;

    public CoffeeCupController(CoffeeCupRepository coffeeCupRepository) {
        this.coffeeCupRepository = coffeeCupRepository;
    }

    @GetMapping("/coffeeCups")
    public Collection<CoffeeCup> getCoffeeCups() {
        return coffeeCupRepository.findAll();
    }

    @PostMapping("/coffeeCups")
    public void addCoffeeCup(@RequestBody CoffeeCup coffeeCup) {
        coffeeCupRepository.add(coffeeCup);
    }

    @DeleteMapping("/coffeeCups")
    public void deleteAll() {
        coffeeCupRepository.deleteAll();
    }
}

package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.Topping;
import com.coffeshop.justcoffee.repositories.interfaces.ToppingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class ToppingController {
    private final ToppingRepository toppingRepository;

    public ToppingController(ToppingRepository toppingRepository) {
        this.toppingRepository = toppingRepository;
    }

    @PostMapping("/toppings")
    public void addTopping(@RequestBody Topping topping) {
        toppingRepository.add(topping);
    }

    @GetMapping("/toppings")
    public Collection<Topping> getToppings() {
        return toppingRepository.findAllToppings();
    }

    @DeleteMapping("/toppings")
    public void deleteAll() {
        toppingRepository.deleteAll();
    }

    @DeleteMapping("/toppings/{id}")
    public void deleteById(@PathVariable long id) {
        toppingRepository.deleteById(id);
    }
}

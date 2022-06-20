package com.coffeeshop.justcoffee.menu.options.repositories;

import com.coffeeshop.justcoffee.menu.options.models.Topping;

import java.util.Collection;

public interface ToppingRepository {
    Collection<Topping> findAllToppings();
    void add(Topping topping);
    Topping findToppingById(long id);
    void deleteAll();
    void deleteById(long id);
}

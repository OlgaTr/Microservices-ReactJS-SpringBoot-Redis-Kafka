package com.coffeshop.justcoffee.repositories;

import com.coffeshop.justcoffee.models.Topping;

import java.util.Collection;

public interface ToppingRepository {
    Collection<Topping> findAllToppings();
    void add(Topping topping);
    Topping findToppingById(long id);
    void deleteAll();
    void deleteById(long id);
}

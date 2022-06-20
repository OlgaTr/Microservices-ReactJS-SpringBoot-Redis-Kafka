package com.coffeeshop.justcoffee.menu.options.repositories;

import com.coffeeshop.justcoffee.menu.options.models.Coffee;

import java.util.Collection;

public interface CoffeeRepository {
    Collection<Coffee> findAllCoffee();
    void add(Coffee coffee);
    void deleteAll();
    void deleteById(long id);
    void deleteByName(String description);
    Coffee findCoffee(long id);
}

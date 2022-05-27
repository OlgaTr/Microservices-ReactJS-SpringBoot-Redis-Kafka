package com.coffeshop.justcoffee.repositories;

import com.coffeshop.justcoffee.models.Coffee;
import com.coffeshop.justcoffee.models.Topping;

import java.util.Collection;

public interface CoffeeRepository {
    Collection<Coffee> findAllCoffee();
    void add(Coffee coffee);
    void deleteAll();
    void deleteById(long id);
    void deleteByName(String description);
    Coffee findCoffee(long id);
}

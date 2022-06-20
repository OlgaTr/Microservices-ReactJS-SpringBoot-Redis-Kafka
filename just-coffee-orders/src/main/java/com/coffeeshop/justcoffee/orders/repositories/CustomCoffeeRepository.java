package com.coffeeshop.justcoffee.orders.repositories;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;

import java.util.Collection;
import java.util.List;

public interface CustomCoffeeRepository {
    Collection<CustomCoffee> findAllCustomCoffee();
    long createCustomCoffee(CustomCoffee customCoffee);
    CustomCoffee getCoffeeOrderById(long coffeeOrderId);
    List<CustomCoffee> getCustomCoffeesById(Long[] coffeeDrinksId);
    CustomCoffee updateCoffeeOrder(CustomCoffee customCoffee);
    void deleteAll();
    void deleteById(long id);
}

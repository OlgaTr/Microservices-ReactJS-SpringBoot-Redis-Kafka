package com.coffeshop.justcoffee.repositories.interfaces;

import com.coffeshop.justcoffee.models.CoffeeDrink;

import java.util.Collection;
import java.util.List;

public interface CoffeeDrinkRepository {
    Collection<CoffeeDrink> findAllCoffeeOrders();
    long createCoffeeOrder(long coffeeId, Long[] toppingsId);
    CoffeeDrink getCoffeeOrderById(long coffeeOrderId);
    List<CoffeeDrink> getCoffeeDrinksById(Long[] coffeeDrinksId);
    CoffeeDrink updateCoffeeOrder(CoffeeDrink coffeeDrink);
    void deleteAll();
    void deleteById(long id);
}

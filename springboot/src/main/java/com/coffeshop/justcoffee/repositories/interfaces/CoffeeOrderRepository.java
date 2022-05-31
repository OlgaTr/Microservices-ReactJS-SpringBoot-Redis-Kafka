package com.coffeshop.justcoffee.repositories.interfaces;

import com.coffeshop.justcoffee.models.CoffeeOrder;

import java.util.Collection;
import java.util.List;

public interface CoffeeOrderRepository {
    Collection<CoffeeOrder> findAllCoffeeOrders();
    long createCoffeeOrder(long coffeeId, Long[] toppingsId);
    CoffeeOrder getCoffeeOrderById(long coffeeOrderId);
    List<CoffeeOrder> getCoffeeDrinksById(Long[] coffeeDrinksId);
    CoffeeOrder updateCoffeeOrder(CoffeeOrder coffeeOrder);
    void deleteAll();
    void deleteById(long id);
}

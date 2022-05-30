package com.coffeshop.justcoffee.repositories.interfaces;

import com.coffeshop.justcoffee.models.CoffeeOrder;

import java.util.Collection;

public interface CoffeeOrderRepository {
    Collection<CoffeeOrder> findAllCoffeeOrders();
    long addCoffeeOrder();
    CoffeeOrder getCoffeeOrderById(long coffeeOrderId);
    CoffeeOrder updateCoffeeOrder(CoffeeOrder coffeeOrder);
    long addCoffeeToCoffeeOrder(long coffeeOrderId, long coffeeId);
    void addToppingsToCoffee(long coffeeOrderId, Long[] toppingsId);
    void deleteAll();
    void deleteById(long id);
}

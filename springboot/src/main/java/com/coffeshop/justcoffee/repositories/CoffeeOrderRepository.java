package com.coffeshop.justcoffee.repositories;

import com.coffeshop.justcoffee.models.CoffeeOrder;

import java.util.Collection;

public interface CoffeeOrderRepository {
    Collection<CoffeeOrder> findAllOrders();
    long addCoffeeOrder();
    void findCoffeeOrder(long id);
    long addCoffeeToCoffeeOrder(long coffeeOrderId, long coffeeId);
    long addToppingToCoffee(long coffeeOrderId, long toppingId);
    void deleteAll();
    void deleteById(long id);
}

package com.coffeshop.justcoffee.repositories.interfaces;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.models.Order;

import java.util.Collection;
import java.util.List;

public interface OrderRepository {
    Collection<Order> findAllOrders();
    long createOrder();
    Order getOrderById(long orderId);
    List<CoffeeOrder> getCoffeeDrinksByOrderId(long orderId);
    void addCoffeeOrderToOrder(long orderId, long coffeeOrderId);
    void deleteById(long orderId);
    void deleteAll();
}

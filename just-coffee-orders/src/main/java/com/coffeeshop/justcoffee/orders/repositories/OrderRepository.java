package com.coffeeshop.justcoffee.orders.repositories;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;
import com.coffeeshop.justcoffee.orders.models.Order;

import java.util.Collection;
import java.util.List;

public interface OrderRepository {
    Collection<Order> findAllOrders();
    long createOrder(String username, Long[] coffeeDrinksId);
    Order getOrderById(long orderId);
    Order getOrderByUsername(String username);
    void updateOrder(Order order);
//    void addCoffeeDrinkToOrder(long orderId, long coffeeDrinkId);
    List<CustomCoffee> getCoffeeDrinksByOrderId(long orderId);
    void deleteById(long orderId);
    void deleteAll();
}

package com.coffeshop.justcoffee.repositories.interfaces;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.models.Order;

import java.util.Collection;
import java.util.List;

public interface OrderRepository {
    Collection<Order> findAllOrders();
    long createOrder(Long[] coffeeDrinksId);
    Order getOrderById(long orderId);
    void updateOrder(Order order);
    List<CoffeeOrder> getCoffeeDrinksByOrderId(long orderId);
    void deleteById(long orderId);
    void deleteAll();
}

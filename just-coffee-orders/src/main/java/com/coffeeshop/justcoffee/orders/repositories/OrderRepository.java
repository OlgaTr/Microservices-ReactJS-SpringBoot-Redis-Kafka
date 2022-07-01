package com.coffeeshop.justcoffee.orders.repositories;

import com.coffeeshop.justcoffee.orders.models.OrderItem;
import com.coffeeshop.justcoffee.orders.models.Order;

import java.util.Collection;
import java.util.List;

public interface OrderRepository {
    Collection<Order> findAllOrders();
    long createOrder(String username, OrderItem[] orderItems);
    Order getOrderById(long orderId);
    List<Order> getOrdersByUsername(String username);
    void updateOrder(Order order);
    List<OrderItem> getOrderItemsByOrderId(long orderId);
    void deleteById(long orderId);
    void deleteAll();
}

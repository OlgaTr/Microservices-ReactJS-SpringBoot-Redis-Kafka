package com.coffeeshop.justcoffee.orders.controllers;

import com.coffeeshop.justcoffee.orders.models.OrderItem;
import com.coffeeshop.justcoffee.orders.models.Order;
import com.coffeeshop.justcoffee.orders.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/orders")
    public long createOrder(Principal principal, @RequestBody OrderItem[] orderItems) {
        String username = principal.getName();
        return orderRepository.createOrder(username, orderItems);
    }

    @GetMapping("/orders")
    public Collection<Order> getOrders() {
        return orderRepository.findAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @GetMapping("/orders/orderItems/{orderId}")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable long orderId) {
        return orderRepository.getOrderItemsByOrderId(orderId);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrderById(@PathVariable long orderId) {
        orderRepository.deleteById(orderId);
    }

    @DeleteMapping("/orders")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}

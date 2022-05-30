package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.models.Order;
import com.coffeshop.justcoffee.repositories.interfaces.OrderRepository;
import org.springframework.web.bind.annotation.*;

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
    public long createOrder() {
        return orderRepository.createOrder();
    }

    @GetMapping("/orders")
    public Collection<Order> getOrders() {
        return orderRepository.findAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @GetMapping("/orders/coffeeDrinks/{orderId}")
    public List<CoffeeOrder> getCoffeeDrinksByOrderId(@PathVariable long orderId) {
        return orderRepository.getCoffeeDrinksByOrderId(orderId);
    }

    @PutMapping("/orders/{orderId}/{coffeeOrderId}")
    public void addCoffeeOrderToOrder(@PathVariable long orderId, @PathVariable long coffeeOrderId) {
        orderRepository.addCoffeeOrderToOrder(orderId, coffeeOrderId);
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

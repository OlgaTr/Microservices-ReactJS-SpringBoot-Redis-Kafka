package com.coffeshop.justcoffee.controllers;

import com.coffeshop.justcoffee.models.CoffeeDrink;
import com.coffeshop.justcoffee.models.Order;
import com.coffeshop.justcoffee.repositories.interfaces.OrderRepository;
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
    public long createOrder(Principal principal, @RequestBody Long[] coffeeDrinksId) {
        String username = principal.getName();
        return orderRepository.createOrder(username, coffeeDrinksId);
    }

    @GetMapping("/orders")
    public Collection<Order> getOrders() {
        return orderRepository.findAllOrders();
    }

    @GetMapping("/orders/userOrder")
    public Order getOrderByUsername(Principal principal) {
        String username = principal.getName();
        return orderRepository.getOrderByUsername(username);
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @GetMapping("/orders/coffeeDrinks/{orderId}")
    public List<CoffeeDrink> getCoffeeDrinksByOrderId(@PathVariable long orderId) {
        return orderRepository.getCoffeeDrinksByOrderId(orderId);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrderById(@PathVariable long orderId) {
        orderRepository.deleteById(orderId);
    }

    @DeleteMapping("/orders")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

//    @PutMapping("/orders/{orderId}/{coffeeDrinkId}")
//    public void addCoffeeDrinkToOrder(@PathVariable long orderId, @PathVariable long coffeeDrinkId) {
//        orderRepository.addCoffeeDrinkToOrder(orderId, coffeeDrinkId);
//    }
}

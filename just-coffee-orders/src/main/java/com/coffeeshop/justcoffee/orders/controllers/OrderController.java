package com.coffeeshop.justcoffee.orders.controllers;

import com.coffeeshop.justcoffee.orders.state_machine.StateMachineService;
import com.coffeeshop.justcoffee.orders.models.Order;
import com.coffeeshop.justcoffee.orders.models.OrderItem;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import com.coffeeshop.justcoffee.orders.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderRepository orderRepository;
    private final StateMachineService stateMachineService;

    public OrderController(OrderRepository orderRepository, StateMachineService stateMachineService) {
        this.orderRepository = orderRepository;
        this.stateMachineService = stateMachineService;
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

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrderById(@PathVariable long orderId) {
        orderRepository.deleteById(orderId);
    }

    @DeleteMapping("/orders")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    @PutMapping("/orders/payment")
    public boolean payForOrder(@RequestBody Map<String, Long> request) {
        long orderId = request.get("orderId");
        stateMachineService.pay(orderId);
        return orderRepository.getOrderById(orderId).getOrderState().equals(OrderState.PAID);
    }
}

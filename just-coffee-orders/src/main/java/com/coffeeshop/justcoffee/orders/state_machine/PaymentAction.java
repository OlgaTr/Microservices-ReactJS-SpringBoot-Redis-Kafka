package com.coffeeshop.justcoffee.orders.state_machine;

import com.coffeeshop.justcoffee.orders.models.Order;
import com.coffeeshop.justcoffee.orders.models.OrderEvent;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import com.coffeeshop.justcoffee.orders.repositories.OrderRepository;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class PaymentAction implements Action<OrderState, OrderEvent> {

    private final OrderRepository orderRepository;

    public PaymentAction(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        long orderId = Long.parseLong(context.getStateMachine().getId());
        Order order = orderRepository.getOrderById(orderId);
        order.setOrderState(OrderState.PAID);
        orderRepository.updateOrder(order);
    }
}

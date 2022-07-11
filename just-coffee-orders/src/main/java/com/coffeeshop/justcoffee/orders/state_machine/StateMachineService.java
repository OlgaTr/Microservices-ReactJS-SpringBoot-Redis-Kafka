package com.coffeeshop.justcoffee.orders.state_machine;

import com.coffeeshop.justcoffee.orders.models.OrderEvent;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import com.coffeeshop.justcoffee.orders.repositories.OrderRepository;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StateMachineService {
    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;

    public StateMachineService(StateMachineFactory<OrderState, OrderEvent> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    public void pay(long orderId) {
        StateMachine<OrderState, OrderEvent> sm = build(orderId);
        sm.sendEvent(Mono.just(MessageBuilder.withPayload(OrderEvent.PAY).build())).subscribe();
    }

    private StateMachine<OrderState, OrderEvent> build(long orderId) {
        StateMachine<OrderState, OrderEvent> machine =  stateMachineFactory.getStateMachine(String.valueOf(orderId));
        machine.start();
        return machine;
    }
}

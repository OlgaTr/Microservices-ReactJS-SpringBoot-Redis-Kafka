package com.coffeeshop.justcoffee.orders.state_machine;

import com.coffeeshop.justcoffee.orders.models.Order;
import com.coffeeshop.justcoffee.orders.models.OrderEvent;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import com.coffeeshop.justcoffee.orders.repositories.OrderRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class StateMachineService {
    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
    private final OrderRepository orderRepository;

    private static final String ORDER_ID_HEADER = "orderId";

    public StateMachineService(StateMachineFactory<OrderState, OrderEvent> stateMachineFactory, OrderRepository orderRepository) {
        this.stateMachineFactory = stateMachineFactory;
        this.orderRepository = orderRepository;
    }

    public StateMachine<OrderState, OrderEvent> pay(long orderId) {
        StateMachine<OrderState, OrderEvent> sm = this.build(orderId);
        Message<OrderEvent> paymentMessage = MessageBuilder.withPayload(OrderEvent.PAY)
                        .setHeader(ORDER_ID_HEADER, orderId)
                        .build();
        sm.sendEvent(Mono.just(paymentMessage)).subscribe();
        return sm;
    }

    private StateMachine<OrderState, OrderEvent> fulfill(long orderId) {
        StateMachine<OrderState, OrderEvent> sm = this.build(orderId);
        Message<OrderEvent> fulfillmentMessage = MessageBuilder.withPayload(OrderEvent.FULFILL)
                .setHeader(ORDER_ID_HEADER, orderId)
                .build();
        sm.sendEvent(Mono.just(fulfillmentMessage)).subscribe();
        return sm;
    }

    private StateMachine<OrderState, OrderEvent> build(long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        StateMachine<OrderState, OrderEvent> sm =  stateMachineFactory.getStateMachine(Long.toString(orderId));
        sm.stop();
        sm.getStateMachineAccessor()
                .doWithAllRegions(access ->
                        access.resetStateMachine(new DefaultStateMachineContext<OrderState, OrderEvent>(
                        order.getOrderState(), null, null, null)));
        sm.getStateMachineAccessor()
                .doWithAllRegions(access ->
                        access.addStateMachineInterceptor(new StateMachineInterceptorAdapter<>() {
                            @Override
                            public void preStateChange(State<OrderState, OrderEvent> state, Message<OrderEvent> message, Transition<OrderState, OrderEvent> transition, StateMachine<OrderState, OrderEvent> stateMachine, StateMachine<OrderState, OrderEvent> rootStateMachine) {
                                Optional.ofNullable(message).ifPresent(msg -> {
                                    Optional.ofNullable(message.getHeaders().getOrDefault(ORDER_ID_HEADER, -1))
                                            .ifPresent(orderId -> {
                                                Order order = orderRepository.getOrderById((long) orderId);
                                                order.setOrderState(state.getId());
                                                orderRepository.updateOrder(order);
                                            });
                                });
                            }
                        }));
        sm.start();
        return sm;
    }
}

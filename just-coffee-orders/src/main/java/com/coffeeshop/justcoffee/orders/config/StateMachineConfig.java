package com.coffeeshop.justcoffee.orders.config;

import com.coffeeshop.justcoffee.orders.models.OrderEvent;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.SUBMITTED).target(OrderState.PAID).event(OrderEvent.PAY)
                .and()
                .withExternal().source(OrderState.PAID).target(OrderState.FULFILLED).event(OrderEvent.FULFILL)
                .and()
                .withExternal().source(OrderState.SUBMITTED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL)
                .and()
                .withExternal().source(OrderState.PAID).target(OrderState.CANCELLED).event(OrderEvent.CANCEL);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.SUBMITTED)
                .state(OrderState.PAID)
//                .stateEntry(OrderState.PAID, paymentAction)
                .end(OrderState.FULFILLED)
                .end(OrderState.CANCELLED);
    }
}

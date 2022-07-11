package com.coffeeshop.justcoffee.orders.config;

import com.coffeeshop.justcoffee.orders.models.OrderEvent;
import com.coffeeshop.justcoffee.orders.models.OrderState;
import com.coffeeshop.justcoffee.orders.state_machine.PaymentAction;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    private final PaymentAction paymentAction;

    public StateMachineConfig(PaymentAction paymentAction) {
        this.paymentAction = paymentAction;
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.SUBMITTED).target(OrderState.PAID).event(OrderEvent.PAY).action(paymentAction)
                .and()
                .withExternal().source(OrderState.PAID).target(OrderState.DELIVERED).event(OrderEvent.DELIVER)
                .and()
                .withExternal().source(OrderState.SUBMITTED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.SUBMITTED)
                .state(OrderState.PAID)
                .end(OrderState.DELIVERED)
                .end(OrderState.CANCELLED);
    }

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
//        StateMachineListenerAdapter<OrderState, OrderEvent> adapter = new StateMachineListenerAdapter<>() {
//            @Override
//            public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
//                super.stateChanged(from, to);
//            }
//        };
//        config
//                .withConfiguration()
//                .autoStartup(false)
//                .listener(adapter);
//    }
}

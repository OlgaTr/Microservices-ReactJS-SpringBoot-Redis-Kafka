package com.coffeeshop.justcoffee.orders.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic newCoffeeDrinkTopic() {
        return TopicBuilder.name("custom-coffee-id")
                .build();
    }
}

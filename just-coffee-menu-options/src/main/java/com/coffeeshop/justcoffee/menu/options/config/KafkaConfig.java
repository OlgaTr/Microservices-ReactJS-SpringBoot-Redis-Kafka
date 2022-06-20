package com.coffeeshop.justcoffee.menu.options.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic justCoffeeTopic() {
        return TopicBuilder.name("custom-coffee")
                .build();
    }
}

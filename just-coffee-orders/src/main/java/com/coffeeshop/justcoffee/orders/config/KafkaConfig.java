package com.coffeeshop.justcoffee.orders.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {


    @Bean
    public NewTopic kRequests() {
        return TopicBuilder.name("kRequests")
                .build();
    }
}

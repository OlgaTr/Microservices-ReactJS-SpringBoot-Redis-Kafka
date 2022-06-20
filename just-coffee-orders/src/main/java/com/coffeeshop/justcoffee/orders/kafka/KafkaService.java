package com.coffeeshop.justcoffee.orders.kafka;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;
import com.coffeeshop.justcoffee.orders.repositories.CustomCoffeeRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaService {

    private final CustomCoffeeRepository customCoffeeRepository;
    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;

    public KafkaService(CustomCoffeeRepository customCoffeeRepository, KafkaTemplate<String, Map<String, String>> kafkaTemplate) {
        this.customCoffeeRepository = customCoffeeRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(
            topics = "custom-coffee",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void receive(Map<String, String> message) {
        CustomCoffee coffee = new CustomCoffee();
        coffee.setDescription(message.get("description"));
        coffee.setPrice(Double.parseDouble(message.get("price")));
        long id = customCoffeeRepository.createCustomCoffee(coffee);
        Map<String, String> coffeeId = new HashMap<>();
        coffeeId.put("customCoffeeId", String.valueOf(id));
        kafkaTemplate.send("custom-coffee-id", coffeeId);
    }
}

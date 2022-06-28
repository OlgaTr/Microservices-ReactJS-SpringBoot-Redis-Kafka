package com.coffeeshop.justcoffee.orders.kafka;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;
import com.coffeeshop.justcoffee.orders.repositories.CustomCoffeeRepository;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaService {

    private final CustomCoffeeRepository customCoffeeRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(CustomCoffeeRepository customCoffeeRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.customCoffeeRepository = customCoffeeRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(
            topics = "custom-coffee",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void receive(String record) {
        Map<String, String> message = new Gson().fromJson(record, Map.class);
        CustomCoffee coffee = new CustomCoffee();
        coffee.setDescription(message.get("description"));
        System.out.println("I an orders service. Description: " + message.get("description"));
        coffee.setPrice(Double.parseDouble(message.get("price")));
        long id = customCoffeeRepository.createCustomCoffee(coffee);
        try {
            kafkaTemplate.send("custom-coffee-id", String.valueOf(id)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

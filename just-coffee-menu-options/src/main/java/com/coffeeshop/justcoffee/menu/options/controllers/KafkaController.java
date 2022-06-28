package com.coffeeshop.justcoffee.menu.options.controllers;

import com.coffeeshop.justcoffee.menu.options.services.KafkaService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaService kafkaService;
    private long customCoffeeId;

    public KafkaController(KafkaTemplate<String, String> kafkaTemplate, KafkaService kafkaService) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaService = kafkaService;
    }

    @PostMapping("/coffee/customCoffee/{coffeeId}")
    public long publish(@PathVariable long coffeeId, @RequestBody Long[] toppingsId) {
        String message = kafkaService.buildMessage(coffeeId, toppingsId);
        System.out.println("I am menu options service. Message is: " + message);
        kafkaTemplate.send("custom-coffee", message);
        System.out.println("I am menu options service. Custom coffee id: " + customCoffeeId);
        return customCoffeeId;
    }

    @KafkaListener(
            topics = "custom-coffee-id",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void receive(String message) {
        customCoffeeId = Long.parseLong(message);
    }
}

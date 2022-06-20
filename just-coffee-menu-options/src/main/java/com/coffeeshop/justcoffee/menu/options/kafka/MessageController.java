package com.coffeeshop.justcoffee.menu.options.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
public class MessageController {

    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;
    private final MessageService messageService;
    private long customCoffeeId;
    private boolean coffeeIdWasReceived = false;

    public MessageController(KafkaTemplate<String, Map<String, String>> kafkaTemplate, MessageService messageService) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageService = messageService;
    }

    @PostMapping("/coffeeOrders/{coffeeId}")
    public long publish(@PathVariable long coffeeId, @RequestBody Long[] toppingsId) {
        Map<String, String> message = messageService.buildMessage(coffeeId, toppingsId);
        kafkaTemplate.send("custom-coffee", message);
        while(!coffeeIdWasReceived) {}
        return customCoffeeId;
    }

    @KafkaListener(
            topics = "custom-coffee-id",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void receive(Map<String, String> message) {
        customCoffeeId = Long.parseLong(message.get("customCoffeeId"));
        coffeeIdWasReceived = true;
    }
}

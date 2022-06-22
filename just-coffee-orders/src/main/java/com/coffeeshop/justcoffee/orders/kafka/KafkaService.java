package com.coffeeshop.justcoffee.orders.kafka;

import com.coffeeshop.justcoffee.orders.models.CustomCoffee;
import com.coffeeshop.justcoffee.orders.repositories.CustomCoffeeRepository;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaService {

    private final CustomCoffeeRepository customCoffeeRepository;

    public KafkaService(CustomCoffeeRepository customCoffeeRepository) {
        this.customCoffeeRepository = customCoffeeRepository;
    }

    @KafkaListener(id="server", topics = "kRequests")
    @SendTo
    public String listen(String record) {
        CustomCoffee coffee = new CustomCoffee();
        Map<String, String> message = new Gson().fromJson(record, Map.class);
        coffee.setDescription(message.get("description"));
        coffee.setPrice(Double.parseDouble(message.get("price")));
        long id = customCoffeeRepository.createCustomCoffee(coffee);
        return String.valueOf(id);
    }
}

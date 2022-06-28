package com.coffeeshop.justcoffee.customers.kafka;

import com.coffeeshop.justcoffee.customers.models.User;
import com.coffeeshop.justcoffee.customers.repositories.UserRepository;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(id="customers", topics = "authRequests")
    public void listen(String record) {
        Map<String, String> message = new Gson().fromJson(record, Map.class);
        User user = new User(message.get("username"), message.get("password"));
        System.out.println("-----------------" + message.get("username"));
        String isAuth = String.valueOf(userRepository.authenticateUser(user));
        try {
            kafkaTemplate.send("authReplies", isAuth).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

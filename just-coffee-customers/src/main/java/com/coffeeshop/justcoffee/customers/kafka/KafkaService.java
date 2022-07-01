package com.coffeeshop.justcoffee.customers.kafka;

import com.coffeeshop.justcoffee.customers.models.User;
import com.coffeeshop.justcoffee.customers.repositories.UserRepository;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaService {

    private final UserRepository userRepository;

    public KafkaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(id="server", topics = "authRequests")
    @SendTo
    public String listen(String record) {
        Map<String, String> message = new Gson().fromJson(record, Map.class);
        User user = new User(message.get("username"), message.get("password"));
        String isAuth = String.valueOf(userRepository.authenticateUser(user));
        return isAuth;
    }
}

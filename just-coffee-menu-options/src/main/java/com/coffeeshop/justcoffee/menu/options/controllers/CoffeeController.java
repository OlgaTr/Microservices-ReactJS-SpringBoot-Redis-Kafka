package com.coffeeshop.justcoffee.menu.options.controllers;

import com.coffeeshop.justcoffee.menu.options.kafka.MessageService;
import com.coffeeshop.justcoffee.menu.options.models.Coffee;
import com.coffeeshop.justcoffee.menu.options.repositories.CoffeeRepository;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;
    private final MessageService messageService;
    private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

    public CoffeeController(CoffeeRepository coffeeRepository, MessageService messageService, ReplyingKafkaTemplate<String, String, String> kafkaTemplate) {
        this.coffeeRepository = coffeeRepository;
        this.messageService = messageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/coffee")
    public void addCoffee(@RequestBody Coffee coffee) {
        coffeeRepository.add(coffee);
    }

    @GetMapping("/coffee")
    public Collection<Coffee> getCoffee() {
        return coffeeRepository.findAllCoffee();
    }

    @GetMapping("/coffee/{id}")
    public Coffee getCoffeeById(@PathVariable long id) {
        return coffeeRepository.findCoffee(id);
    }

    @DeleteMapping("/coffee")
    public void deleteAll() {
        coffeeRepository.deleteAll();
    }

    @DeleteMapping("/coffee/{id}")
    public void deleteById(@PathVariable long id) {
        coffeeRepository.deleteById(id);
    }

    @PostMapping("/coffee/customCoffee/{coffeeId}")
    public long publish(@PathVariable long coffeeId, @RequestBody Long[] toppingsId) throws ExecutionException, InterruptedException, TimeoutException {
        Map<String, String> message = messageService.buildMessage(coffeeId, toppingsId);
//        new ObjectMapper().writeValueAsString(message);
        String jsonMessage = new Gson().toJson(message);
        ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", jsonMessage);
        RequestReplyFuture<String, String, String> replyFuture = kafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        return Long.parseLong(consumerRecord.value());
    }
}

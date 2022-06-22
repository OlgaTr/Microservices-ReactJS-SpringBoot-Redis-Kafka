package com.coffeeshop.justcoffee.menu.options.kafka;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@CrossOrigin("*")
public class MessageController {

    private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;
    private final MessageService messageService;

    public MessageController(ReplyingKafkaTemplate<String, String, String> kafkaTemplate, MessageService messageService) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageService = messageService;
    }

    @PostMapping("/coffeeOrders/{coffeeId}")
    public long publish(@PathVariable long coffeeId, @RequestBody Long[] toppingsId) throws ExecutionException, InterruptedException, TimeoutException {
        Map<String, String> message = messageService.buildMessage(coffeeId, toppingsId);
        String jsonMessage = new Gson().toJson(message);
        ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", jsonMessage);
        RequestReplyFuture<String, String, String> replyFuture = kafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        return Long.parseLong(consumerRecord.value());
    }
}

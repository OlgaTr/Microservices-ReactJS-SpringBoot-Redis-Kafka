package com.coffeeshop.justcoffee.orders.security;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

    public CustomAuthenticationFilter(ReplyingKafkaTemplate<String, String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        try {
            if (requestAuthentication(username, password)) {
                GrantedAuthority a = new SimpleGrantedAuthority("user");
                var auth = new CustomAuthentication(username, null, List.of(a));
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            } else {
                throw new BadCredentialsException("Wrong credentials.");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private boolean requestAuthentication(String username, String password) throws ExecutionException, InterruptedException, TimeoutException {
        Map<String, String> authMessage = new HashMap<>();
        authMessage.put("username", username);
        authMessage.put("password", password);
        String jsonAuthMessage = new Gson().toJson(authMessage);
        ProducerRecord<String, String> record = new ProducerRecord<>("authRequests", jsonAuthMessage);
        RequestReplyFuture<String, String, String> replyFuture = kafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        return Boolean.parseBoolean(consumerRecord.value());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestedPath = request.getRequestURI();
        String requestedMethod = request.getMethod();
        return getOpenUrls().get(requestedMethod).contains(requestedPath);
    }

    private Map<String, List<String>> getOpenUrls(){
        Map<String, List<String>> openUrls = new HashMap<>();
        openUrls.put("GET", List.of("/orders", "/coffeeOrders"));
        openUrls.put("POST", List.of("/customCoffees"));
        openUrls.put("PUT", Collections.emptyList());
        openUrls.put("DELETE", List.of("/orders", "/coffeeOrders"));
        return openUrls;
    }
}

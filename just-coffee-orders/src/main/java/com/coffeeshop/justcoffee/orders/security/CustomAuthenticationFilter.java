package com.coffeeshop.justcoffee.orders.security;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private boolean isAuth = false;
//    private boolean replyWasReceived = false;
    private List<String> openGetUrls = List.of("/orders");
    private List<String> openPostUrls = List.of("/customCoffees");
    private List<String> openDeleteUrls = List.of("/orders", "/coffeeOrders");

    public CustomAuthenticationFilter(KafkaTemplate<String, String> kafkaTemplate) {
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
                throw new RuntimeException();
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
        kafkaTemplate.send(record);
//        while (!replyWasReceived) {}
        return isAuth;
    }

    @KafkaListener(id = "customers", topics = "authReplies")
    public void listen(String record) {
        isAuth = Boolean.parseBoolean(record);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestedPath = request.getRequestURI();
        String requestedMethod = request.getMethod();
        boolean shouldNotFilter = false;
        if (openPostUrls.contains(requestedPath) && "POST".equals(requestedMethod)) {
            shouldNotFilter = true;
        } else if (openGetUrls.contains(requestedPath) && "GET".equals(requestedMethod)) {
            shouldNotFilter = true;
        } else if (openDeleteUrls.contains(requestedPath) &&
                "DELETE".equals(requestedMethod)) {
            shouldNotFilter = true;
        }
        return shouldNotFilter;
    }
}

package com.coffeeshop.justcoffee.menu.options.kafka;

import com.coffeeshop.justcoffee.menu.options.models.Coffee;
import com.coffeeshop.justcoffee.menu.options.models.Topping;
import com.coffeeshop.justcoffee.menu.options.repositories.CoffeeRepository;
import com.coffeeshop.justcoffee.menu.options.repositories.ToppingRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final CoffeeRepository coffeeRepository;
    private final ToppingRepository toppingRepository;

    public MessageService(CoffeeRepository coffeeRepository, ToppingRepository toppingRepository) {
        this.coffeeRepository = coffeeRepository;
        this.toppingRepository = toppingRepository;
    }

    public Map<String, String> buildMessage(long coffeeId, Long[] toppingsId) {
        String description = buildDescription(coffeeId, toppingsId);
        double price = calculatePrice(coffeeId, toppingsId);
        Map<String, String> message = new HashMap<>();
        message.put("description", description);
        message.put("price", String.valueOf(price));
        return message;
    }

    private String buildDescription(long coffeeId, Long[] toppingsId) {
        Coffee coffee = coffeeRepository.findCoffee(coffeeId);
        List<Topping> toppings = getToppingsById(Arrays.asList(toppingsId));
        StringBuilder description = new StringBuilder(coffee.type);
        if (toppings.size() > 0) {
            description.append(" with ");
            List<String> types = toppings.stream().map(Topping::getType).collect(Collectors.toList());
            description.append(String.join(", ", types));
        }
        return description.toString();
    }

    private double calculatePrice(long coffeeId, Long[] toppingsId) {
        Coffee coffee = coffeeRepository.findCoffee(coffeeId);
        double coffeePrice = coffee.getPrice();
        List<Topping> toppings = getToppingsById(Arrays.asList(toppingsId));
        double toppingsPrice = toppings.stream().map(Topping::getPrice).mapToDouble(price -> price).sum();
        return (double) Math.round((coffeePrice + toppingsPrice)*100)/100;
    }

    private List<Topping> getToppingsById(List<Long> toppingsId) {
        return toppingsId.stream()
                .map(toppingId -> toppingRepository.findToppingById(toppingId))
                .collect(Collectors.toList());
    }
}

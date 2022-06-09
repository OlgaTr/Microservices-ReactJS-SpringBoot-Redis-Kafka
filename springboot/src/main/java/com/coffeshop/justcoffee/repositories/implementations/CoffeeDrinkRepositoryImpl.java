package com.coffeshop.justcoffee.repositories.implementations;

import com.coffeshop.justcoffee.models.Coffee;
import com.coffeshop.justcoffee.models.CoffeeDrink;
import com.coffeshop.justcoffee.models.Topping;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeDrinkRepository;
import com.coffeshop.justcoffee.repositories.interfaces.CoffeeRepository;
import com.coffeshop.justcoffee.repositories.interfaces.ToppingRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.coffeshop.justcoffee.utils.IdGenerator.generateId;

@Repository
public class CoffeeDrinkRepositoryImpl implements CoffeeDrinkRepository {
    private static final String KEY = "COFFEE_ORDER";
    private final RedisTemplate<String, Object> coffeeOrderTemplate;
    private final CoffeeRepository coffeeRepository;
    private final ToppingRepository toppingRepository;
    private HashOperations coffeeOrderHashOperations;

    public CoffeeDrinkRepositoryImpl(RedisTemplate<String, Object> orderTemplate, CoffeeRepository coffeeRepository, ToppingRepository toppingRepository) {
        this.coffeeOrderTemplate = orderTemplate;
        this.coffeeRepository = coffeeRepository;
        this.toppingRepository = toppingRepository;
    }

    @PostConstruct
    private void init() {
        coffeeOrderHashOperations = coffeeOrderTemplate.opsForHash();
    }

    @Override
    public Collection<CoffeeDrink> findAllCoffeeOrders() {
        return (Collection<CoffeeDrink>) coffeeOrderHashOperations.entries(KEY).values();
    }

    @Override
    public long createCoffeeOrder(long coffeeId, Long[] toppingsId) {
        CoffeeDrink coffeeDrink = new CoffeeDrink(coffeeId, Arrays.asList(toppingsId));
        coffeeDrink.setDescription(buildDescription(coffeeDrink));
        coffeeDrink.setPrice(calculatePrice(coffeeDrink));
        long generatedId = generateId();
        coffeeDrink.setId(generatedId);
        coffeeOrderHashOperations.put(KEY, generatedId, coffeeDrink);
        return generatedId;
    }

    @Override
    public CoffeeDrink getCoffeeOrderById(long coffeeOrderId) {
        return (CoffeeDrink) coffeeOrderHashOperations.get(KEY, coffeeOrderId);
    }

    @Override
    public List<CoffeeDrink> getCoffeeDrinksById(Long[] coffeeDrinksId) {
        return Arrays.stream(coffeeDrinksId)
                .map(id -> (CoffeeDrink) coffeeOrderHashOperations.get(KEY, id))
                .collect(Collectors.toList());
    }

    @Override
    public CoffeeDrink updateCoffeeOrder(CoffeeDrink coffeeDrink) {
        long coffeeOrderId = coffeeDrink.getId();
        coffeeOrderHashOperations.delete(KEY, coffeeOrderId);
        coffeeOrderHashOperations.put(KEY, coffeeOrderId, coffeeDrink);
        return null;
    }

    @Override
    public void deleteAll() {
        coffeeOrderHashOperations.keys(KEY).stream().forEach(k -> coffeeOrderHashOperations.delete(KEY, k));
    }

    @Override
    public void deleteById(long id) {
        coffeeOrderHashOperations.delete(KEY, id);
    }

    private List<Topping> getToppingsById(List<Long> toppingsId) {
        return toppingsId.stream()
                .map(toppingId -> toppingRepository.findToppingById(toppingId))
                .collect(Collectors.toList());
    }

    private String buildDescription(CoffeeDrink coffeeDrink) {
        Coffee coffee = coffeeRepository.findCoffee(coffeeDrink.getCoffeeId());
        List<Topping> toppings = getToppingsById(coffeeDrink.getToppingsId());
        StringBuilder description = new StringBuilder(coffee.type);
        if (toppings.size() > 0) {
            description.append(" with ");
            List<String> types = toppings.stream().map(Topping::getType).collect(Collectors.toList());
            description.append(String.join(", ", types));
        }
        return description.toString();
    }

    private double calculatePrice(CoffeeDrink coffeeDrink) {
        Coffee coffee = coffeeRepository.findCoffee(coffeeDrink.getCoffeeId());
        double coffeePrice = coffee.getPrice();
        List<Topping> toppings = getToppingsById(coffeeDrink.getToppingsId());
        double toppingsPrice = toppings.stream().map(Topping::getPrice).mapToDouble(price -> price).sum();
        return (double) Math.round((coffeePrice + toppingsPrice)*100)/100;
    }
}

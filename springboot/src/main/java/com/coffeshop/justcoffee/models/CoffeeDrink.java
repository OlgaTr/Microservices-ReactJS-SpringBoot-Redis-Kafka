package com.coffeshop.justcoffee.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoffeeDrink implements Serializable {

//    @Id
    private long id;
    private double price;
    private String description;
    private long coffeeId;
    private List<Long> toppingsId = new ArrayList<>();

    public CoffeeDrink() {
    }

    public CoffeeDrink(long coffeeId, List<Long> toppingsId) {
        this.coffeeId = coffeeId;
        this.toppingsId = toppingsId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public List<Long> getToppingsId() {
        return toppingsId;
    }

    public void addToppingToCoffee(long toppingId) {
        toppingsId.add(toppingId);
    }
}

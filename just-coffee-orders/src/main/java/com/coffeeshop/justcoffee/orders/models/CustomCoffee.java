package com.coffeeshop.justcoffee.orders.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomCoffee implements Serializable {

//    @Id
    private long id;
    private double price;
    private String description;

    public CustomCoffee() {
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
}

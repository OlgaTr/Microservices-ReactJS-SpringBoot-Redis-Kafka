package com.coffeeshop.justcoffee.orders.models;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private double price;
    private String description;

    public OrderItem() {
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

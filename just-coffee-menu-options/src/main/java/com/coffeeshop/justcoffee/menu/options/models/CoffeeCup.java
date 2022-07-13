package com.coffeeshop.justcoffee.menu.options.models;

import java.io.Serializable;

public class CoffeeCup implements Serializable {

    private long id;
    private String size;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

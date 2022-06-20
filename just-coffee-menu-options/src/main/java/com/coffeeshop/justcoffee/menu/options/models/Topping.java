package com.coffeeshop.justcoffee.menu.options.models;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Topping implements Serializable {

//    @Indexed
//    @Id
    private long id;
    private String type;
//    private long coffeId;
    private double price;

    public Topping(long id, String type, double price) {
//        this.coffeId = coffeId;
        this.id = id;
        this.type = type;
        this.price = price;
    }

//    public long getCoffeId() {
//        return coffeId;
//    }
//
//    public void setCoffeId(long coffeId) {
//        this.coffeId = coffeId;
//    }
//
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String description) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

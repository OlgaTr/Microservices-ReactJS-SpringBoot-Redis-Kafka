package com.coffeeshop.justcoffee.menu.options.models;

import java.io.Serializable;

public class Topping implements Serializable {

//    @Indexed
//    @Id
    private long id;
    private String type;
    private double price;

    public Topping(long id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }


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

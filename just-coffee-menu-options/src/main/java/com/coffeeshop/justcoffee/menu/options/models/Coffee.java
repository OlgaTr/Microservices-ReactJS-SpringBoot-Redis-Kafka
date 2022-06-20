package com.coffeeshop.justcoffee.menu.options.models;

import java.io.Serializable;

//@RedisHash("coffee")
public class Coffee implements Serializable {

//    @Id
    private long id;
    public String type;
    public double price;

    public Coffee(String type, double price, long id) {
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
        this.type = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}

package com.coffeshop.justcoffee.models;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

//@RedisHash("coffee")
public class Coffee implements Serializable {

    @Id
    private long id;
    public String type;
    public String price;


    public Coffee() {
    }

    public Coffee(long id, String type, String price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String description) {
        this.type = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

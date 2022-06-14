package com.coffeshop.justcoffee.models;

import java.io.Serializable;

public class User implements Serializable {

//    @Id
    private String username;
    private String password;
    private String role;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

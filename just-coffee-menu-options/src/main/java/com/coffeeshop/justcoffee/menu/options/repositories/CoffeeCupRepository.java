package com.coffeeshop.justcoffee.menu.options.repositories;

import com.coffeeshop.justcoffee.menu.options.models.CoffeeCup;

import java.util.Collection;

public interface CoffeeCupRepository {
    Collection<CoffeeCup> findAll();
    void add(CoffeeCup coffeeCup);
    void deleteAll();

}

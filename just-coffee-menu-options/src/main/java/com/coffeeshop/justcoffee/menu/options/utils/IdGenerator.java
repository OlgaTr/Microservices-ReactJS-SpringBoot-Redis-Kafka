package com.coffeeshop.justcoffee.menu.options.utils;

import java.util.Random;

public class IdGenerator {
    public static long generateId() {
        return new Random().nextLong(0, 10000000000000l);
    }
}

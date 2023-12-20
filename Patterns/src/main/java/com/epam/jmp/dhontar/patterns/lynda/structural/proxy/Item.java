package com.epam.jmp.dhontar.patterns.lynda.structural.proxy;

public class Item {
     private final String name;
     private final double price;
     private final int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + ", price: $" + price + ", quantity: " + quantity;
    }
}

package com.epam.jmp.dhontar.patterns.lynda.structural.proxy;

public class Store {

    private final String name;
    private final String location;
    private final Inventory inventory;

    public Store(String name, String location, Inventory inventory) {
        this.name = name;
        this.location = location;
        this.inventory = inventory;
    }

    public void printName() {
        System.out.println(name);
    }

    public void printLocation() {
        System.out.println(location);
    }

    public void printInventory() {
        for ( var item : inventory.getInventory()) {
            System.out.println(item);
        }
    }
}

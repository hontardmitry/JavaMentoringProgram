package com.epam.jmp.dhontar.patterns.lynda.structural.proxy;

public class MainForProxy {

    public static void main(String[] args) {
        Inventory inventory = new SuperInventoryProxy();
        Store store = new Store( "Healthy Wholefoods", "Los Angeles",
                inventory);

        store.printName();
        store.printLocation();
        store.printInventory();
    }
}

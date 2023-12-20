package com.epam.jmp.dhontar.patterns.lynda.structural.proxy;

import java.util.List;

public class SuperInventoryProxy implements Inventory {
    private Inventory inventory;

    @Override
    public List<Item> getInventory() {
        if (inventory == null) {
            inventory = new SuperInventory();
        }
        return inventory.getInventory();
    }
}

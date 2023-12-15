package com.epam.jmp.dhontar.patterns.lynda.structural.decorator;

import java.util.List;

public class ExtraCheese implements Pizza{

    private final Pizza pizza;
    private final List<String> toppings;

    public ExtraCheese(Pizza pizza) {
        this.pizza = pizza;
        this.toppings = pizza.getTopping();
        toppings.add("extra cheese");
    }

    @Override
    public List<String> getTopping() {
        return toppings;
    }

    @Override
    public String getName() {
        return pizza.getName();
    }
}

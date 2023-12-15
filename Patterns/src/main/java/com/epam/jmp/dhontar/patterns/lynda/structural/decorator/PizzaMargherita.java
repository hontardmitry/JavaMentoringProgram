package com.epam.jmp.dhontar.patterns.lynda.structural.decorator;

import java.util.ArrayList;
import java.util.List;

public class PizzaMargherita implements Pizza {

    private static final List<String> TOPPINGS = new ArrayList<>();
    private static final String NAME = "Pepperoni";

    public PizzaMargherita() {
        TOPPINGS.add("cheese");
        TOPPINGS.add("tomato");
    }
    @Override
    public List<String> getTopping() {
        return TOPPINGS;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

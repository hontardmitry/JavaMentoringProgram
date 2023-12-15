package com.epam.jmp.dhontar.patterns.lynda.structural.decorator;

import java.util.ArrayList;
import java.util.List;

public class PizzaHawaiian implements Pizza {

    private static final List<String> TOPPINGS = new ArrayList<>();
    private static final String NAME = "Hawaiian";

    public PizzaHawaiian() {
        TOPPINGS.add("cheese");
        TOPPINGS.add("tomato");
        TOPPINGS.add("ham");
        TOPPINGS.add("pineapple");
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

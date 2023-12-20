package com.epam.jmp.dhontar.patterns.lynda.structural.adapter;

public class Adapter implements PriceCalculator{

    UKCarPriceCalculator ukCarPriceCalculator;

    public Adapter(UKCarPriceCalculator ukCarPriceCalculator) {
        this.ukCarPriceCalculator = ukCarPriceCalculator;
    }

    @Override
    public String calculatePrice() {
        return ukCarPriceCalculator.getPrice() + " GBP";
    }
}

package com.epam.jmp.dhontar.patterns.lynda.structural.adapter;

public class Main {

    public static void main(String[] args) {
        PriceCalculatorImpl priceCalculator = new PriceCalculatorImpl();
        printVehiclePrice(priceCalculator);

        UKCarPriceCalculator ukCarPriceCalculator = new UKCarPriceCalculator();
        Adapter adapter = new Adapter(ukCarPriceCalculator);
        printVehiclePrice(adapter);
    }

    public static void printVehiclePrice(PriceCalculator calculator) {
        String price = calculator.calculatePrice();
        System.out.println("The price of the vehicle is: " + price);
    }
}

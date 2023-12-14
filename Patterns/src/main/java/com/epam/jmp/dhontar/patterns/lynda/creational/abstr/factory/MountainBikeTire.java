package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class MountainBikeTire extends Tire {
    private static final String WIDTH = "WIDE";
    private static final String PRESSURE = "MEDIUM";

    @Override
    void getDescription() {
        System.out.printf("Mountain bike tire width: %s, pressure: %s\n",
                WIDTH, PRESSURE);
    }
}

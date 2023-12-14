package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class RoadBikeTire extends Tire {

    private static final String WIDTH = "NARROW";
    @Override
    void getDescription() {
        System.out.printf("Road bike tire width: %s\n", WIDTH);
    }
}

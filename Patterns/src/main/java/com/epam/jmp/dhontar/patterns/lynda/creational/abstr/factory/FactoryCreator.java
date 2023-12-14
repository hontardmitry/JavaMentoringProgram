package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class FactoryCreator {

    public static BikeFactory createFactory(String type) {

        if ("mountain bike".equalsIgnoreCase(type)) {
            return new MountainBikeFactory();
        } else if ("road bike".equalsIgnoreCase(type)) {
            return new RoadBikeFactory();
        } else {
            throw new IllegalArgumentException("Incorrect factory type is specified");
        }
    }
}

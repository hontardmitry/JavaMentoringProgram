package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class RoadBikeFactory extends BikeFactory {

    @Override
    BikePart create(String type) {
        if ("tire".equalsIgnoreCase(type)) {
            return new RoadBikeTire();
        } else if ("handlebar".equalsIgnoreCase(type)) {
            return new RoadBikeHandlebar();
        } else {
            return null;
        }
    }
}

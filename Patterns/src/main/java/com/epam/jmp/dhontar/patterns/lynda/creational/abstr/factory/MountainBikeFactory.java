package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class MountainBikeFactory extends BikeFactory {

    @Override
    BikePart create(String type) {
        if ("tire".equalsIgnoreCase(type)) {
            return new MountainBikeTire();
        } else if ("handlerbar".equalsIgnoreCase(type)) {
            return new MountainBikeHandlebar();
        } else {
            return null;
        }
    }
}

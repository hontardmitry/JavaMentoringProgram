package com.epam.jmp.dhontar.patterns.lynda.structural.flyweight;

public interface Animal {

    String getAnimalType();
    int[] getLocation();
    void setLocation(int latitude, int longitude);
}

package com.epam.jmp.dhontar.patterns.lynda.structural.flyweight;

public class Lion implements Animal {

    private int[] location = new int[2];

    @Override
    public String getAnimalType() {
        return "Lion";
    }

    @Override
    public int[] getLocation() {
        return location;
    }

    @Override
    public void setLocation(int latitude, int longitude) {
        location[0] = latitude;
        location[1] = longitude;
    }
}

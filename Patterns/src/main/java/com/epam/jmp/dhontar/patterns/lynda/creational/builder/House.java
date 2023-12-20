package com.epam.jmp.dhontar.patterns.lynda.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class House {

    private List listOfRooms;

    public House(List<Room> listOfRooms) {
        this.listOfRooms = listOfRooms;
    }
}

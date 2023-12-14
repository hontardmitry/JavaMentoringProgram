package com.epam.jmp.dhontar.patterns.lynda.creational.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

public class Architect {

    public static void main(String[] args) {

        var rooms = new RoomListBuilder().addList()
                .addRoom().setCeilingHeight(3).setWallColor(Color.BLUE).addRoomToList()
                .addRoom().setDimensions(new Dimension(5, 3)).setWallColor(Color.DARK_GRAY).addRoomToList()
                .buildList();

        House house = new House(rooms);
    }
}

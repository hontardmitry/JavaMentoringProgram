package com.epam.jmp.dhontar.patterns.lynda.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class RoomListBuilder {

    private List<Room> listOfRooms;

    public RoomListBuilder addList() {
        this.listOfRooms = new ArrayList<>();
        return this;
    }

    public RoomListBuilder addRoom(Room room) {
        listOfRooms.add(room);
        return this;
    }

    public RoomBuilder addRoom() {
        return new RoomBuilder(this);
    }

    public List<Room> buildList() {
        return listOfRooms;
    }
}

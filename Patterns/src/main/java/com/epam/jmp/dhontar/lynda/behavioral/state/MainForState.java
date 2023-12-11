package com.epam.jmp.dhontar.lynda.behavioral.state;

public class MainForState {

    public static void main(String[] args) {
        Fan fan = new Fan(new FanStateLow());

        fan.turnUp();
        fan.turnUp();
        fan.turnDown();
        fan.turnDown();
        fan.turnDown();
        fan.turnUp();
    }
}

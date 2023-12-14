package com.epam.jmp.dhontar.patterns.lynda.behavioral.state;

public class FanStateLow implements FanState {
    @Override
    public void turnUp(Fan fan) {
        fan.setState(new FanStateMedium());
        System.out.println("Fan is now on medium");
    }

    @Override
    public void turnDown(Fan fan) {

    }
}

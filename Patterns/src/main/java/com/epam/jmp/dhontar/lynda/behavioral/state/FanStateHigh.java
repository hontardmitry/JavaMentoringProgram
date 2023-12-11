package com.epam.jmp.dhontar.lynda.behavioral.state;

public class FanStateHigh implements FanState{
    @Override
    public void turnUp(Fan fan) {

    }

    @Override
    public void turnDown(Fan fan) {
        fan.setState(new FanStateMedium());
        System.out.println("Fan is on medium");
    }
}

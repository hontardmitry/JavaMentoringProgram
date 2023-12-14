package com.epam.jmp.dhontar.patterns.lynda.behavioral.state;

public class FanStateMedium implements FanState {
    @Override
    public void turnUp(Fan fan) {
        fan.setState(new FanStateHigh());
        System.out.println("Fan is now on low");
    }

    @Override
    public void turnDown(Fan fan) {
        fan.setState(new FanStateLow());
        System.out.println("Fan is now on low");
    }
}

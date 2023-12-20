package com.epam.jmp.dhontar.patterns.lynda.structural.facade;

public class CarFacade {

    public void drive(){
        Ignition ignition = new Ignition();
        Clutch clutch = new Clutch();
        Accelerator accelerator = new Accelerator();
        GearStick gearStick = new GearStick();
        Handbreak handbreak = new Handbreak();

        ignition.turnOn();
        clutch.press();
        gearStick.changeGear(1);
        accelerator.press();
        clutch.lift();
        handbreak.pushDown();
        accelerator.press();
        clutch.lift();
    }
}

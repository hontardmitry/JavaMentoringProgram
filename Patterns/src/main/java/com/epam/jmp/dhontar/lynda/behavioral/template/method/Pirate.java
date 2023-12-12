package com.epam.jmp.dhontar.lynda.behavioral.template.method;

public class Pirate extends Character{

    @Override
    public void pickUpWeapon(){
        System.out.println("Pirate picks up a sword");
    }

    @Override
    public void defend(){
        System.out.println("Pirate defends with a sword");
    }

    @Override
    public void returnHome(){
        System.out.println("Return to the ship");
    }
}

package com.epam.jmp.dhontar.patterns.lynda.behavioral.template.method;

public class Troll extends Character{
    @Override
    public void pickUpWeapon(){
        System.out.println("Troll picks up a club");
    }

    @Override
    public void defend(){
        System.out.println("Troll defends with a club");
    }

    @Override
    public void returnHome(){
        System.out.println("Return to the mountain");
    }
}

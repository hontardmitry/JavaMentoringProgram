package com.epam.jmp.dhontar.lynda.behavioral.template.method;

public abstract class Character {

    public void pickUpWeapon(){}

    public void defend(){}

    public void returnHome(){}

    public void defendAgainstAttack(){
        pickUpWeapon();
        defend();
        returnHome();
        System.out.println();
    }
}

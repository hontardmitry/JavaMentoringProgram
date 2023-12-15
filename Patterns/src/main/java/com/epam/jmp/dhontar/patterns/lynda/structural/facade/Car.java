package com.epam.jmp.dhontar.patterns.lynda.structural.facade;

public class Car {

    public static void main(String[] args) {
        CarFacade carFacade = new CarFacade();
        carFacade.drive();
    }
}

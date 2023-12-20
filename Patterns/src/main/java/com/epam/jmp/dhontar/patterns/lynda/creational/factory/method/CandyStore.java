package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

public class CandyStore {
    public static final CandyFactory CHOCOLATE_FACTORY = new ChocolateFactory();
    public static final CandyFactory HARD_CANDY_FACTORY = new HardCandyFactory();
    public static void main(String[] args) {
        CHOCOLATE_FACTORY.getCandyPackage(12, "white");
        CHOCOLATE_FACTORY.getCandyPackage(7, "dark");
        CHOCOLATE_FACTORY.getCandyPackage(1, "milk");

        HARD_CANDY_FACTORY.getCandyPackage(20, "lollipop");
        HARD_CANDY_FACTORY.getCandyPackage(10, "candycane");
        HARD_CANDY_FACTORY.getCandyPackage(6, "peppermint");
    }
}

package com.epam.jmp.dhontar.patterns.lynda.creational.singleton;

public class ResourceManager {

    public static void main(String[] args) {

        Thread threadOne = new Thread(PrintsPooler::getInstance);
        Thread threadTwo = new Thread(PrintsPooler::getInstance);
        threadOne.start();
        threadTwo.start();
    }
}

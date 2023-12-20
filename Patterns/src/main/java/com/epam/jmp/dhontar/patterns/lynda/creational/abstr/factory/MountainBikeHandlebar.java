package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class MountainBikeHandlebar extends Handlebar {

    private static final String TYPE = "FLAT";
    @Override
    void getDescription() {
        System.out.printf("Mountain bike hadlebar. Type: %s\n", TYPE);
    }
}

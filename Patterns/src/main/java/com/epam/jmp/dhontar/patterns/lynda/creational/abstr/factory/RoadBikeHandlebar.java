package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class RoadBikeHandlebar extends Handlebar {

    private static final String TYPE = "DROP";
    @Override
    void getDescription() {
        System.out.printf("Road bike hadlebar. Type: %s\n", TYPE);
    }
}

package com.epam.jmp.dhontar.patterns.lynda.behavioral.mediator;

public class MainForMediator {

    public static void main(String[] args) {
        var mediator = new Mediator();
        mediator.buy("pens", 3);
    }
}

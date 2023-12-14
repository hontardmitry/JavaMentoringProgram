package com.epam.jmp.dhontar.patterns.lynda.behavioral.mediator;

public class Driver {

    public void deliver(String item, int quantity, Customer customer) {
        System.out.printf("%d %s out for delivery to %s\n",
                quantity, item, customer.getAddress());
    }
}

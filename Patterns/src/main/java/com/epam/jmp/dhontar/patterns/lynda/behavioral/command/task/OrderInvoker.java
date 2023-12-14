package com.epam.jmp.dhontar.patterns.lynda.behavioral.command.task;

public class OrderInvoker {

    public void invoke(OrderCommand command) {
        command.execute();
    }
}

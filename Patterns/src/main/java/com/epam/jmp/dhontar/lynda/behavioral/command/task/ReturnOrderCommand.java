package com.epam.jmp.dhontar.lynda.behavioral.command.task;

public class ReturnOrderCommand implements OrderCommand{

    private final Jacket jacket;

    public ReturnOrderCommand(Jacket jacket) {
        this.jacket = jacket;
    }
    @Override
    public void execute() {
        jacket.returnOrder();
    }
}

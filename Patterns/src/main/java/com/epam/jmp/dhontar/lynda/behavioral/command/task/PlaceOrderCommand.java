package com.epam.jmp.dhontar.lynda.behavioral.command.task;

public class PlaceOrderCommand implements OrderCommand{

    private final Jacket jacket;

    public PlaceOrderCommand(Jacket jacket) {
        this.jacket = jacket;
    }
    @Override
    public void execute() {
        jacket.placeOrder();
    }
}

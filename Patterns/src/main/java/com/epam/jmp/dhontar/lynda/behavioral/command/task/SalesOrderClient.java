package com.epam.jmp.dhontar.lynda.behavioral.command.task;

public class SalesOrderClient {

    private static final Jacket JACKET = new Jacket();

    public static void main(String[] args) {

        OrderInvoker placeOrderInvoker = new OrderInvoker();
        OrderInvoker returnOrderInvoker = new OrderInvoker();

        placeOrderInvoker.invoke(new PlaceOrderCommand(JACKET));
        returnOrderInvoker.invoke(new ReturnOrderCommand(JACKET));
    }
}

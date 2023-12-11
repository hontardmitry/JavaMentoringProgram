package com.epam.jmp.dhontar.lynda.behavioral.mediator;

public class Mediator {

    private final Customer customer;
    private final Driver driver;
    private final ECommerceSite site;

    public Mediator() {
        customer = new Customer("123 Sunny Street");
        driver = new Driver();
        site = new ECommerceSite();
    }

    public void buy(String item, int quantity){
        if(site.checkInStock(item, quantity)) {
            site.sell(item, quantity);
            driver.deliver(item, quantity, customer);
        }
    }
}

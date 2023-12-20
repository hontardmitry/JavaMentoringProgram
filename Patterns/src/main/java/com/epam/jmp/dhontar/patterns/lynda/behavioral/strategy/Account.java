package com.epam.jmp.dhontar.patterns.lynda.behavioral.strategy;

public interface Account {

    void makePayment(int amount);

    Account bankAccount = (int amount) -> System.out.printf("Payment of $%d made from bank account.\n", amount);

    Account payPalAccount =  (int amount) -> System.out.printf("Payment of $%d made from PayPal.\n", amount);
}

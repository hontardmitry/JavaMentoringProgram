package com.epam.jmp.dhontar.patterns.lynda.behavioral.strategy;

public class Transaction {

    private int amount;

    public void pay(Account account, int amount) {
       account.makePayment(amount);
    }
}

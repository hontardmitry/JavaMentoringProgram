package com.epam.jmp.dhontar.lynda.behavioral.strategy;

public class Transaction {

    private int amount;

    public void pay(Account account, int amount) {
       account.makePayment(amount);
    }
}

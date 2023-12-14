package com.epam.jmp.dhontar.patterns.lynda.behavioral.strategy;



public class MainForStrategy {

    public static void main(String[] args) {
        var transaction = new Transaction();
        transaction.pay(Account.bankAccount, 100);
        transaction.pay(Account.payPalAccount, 38);
    }
}

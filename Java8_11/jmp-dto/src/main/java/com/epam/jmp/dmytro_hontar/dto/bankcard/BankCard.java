package com.epam.jmp.dmytro_hontar.dto.bankcard;

import static com.epam.jmp.dmytro_hontar.dto.bankcard.card_number_generator.BankCardNumberGenerator.generateBankCardNumber;

import com.epam.jmp.dmytro_hontar.dto.User;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BankCard {
    private final String number;
    private final User user;
    private BigDecimal amount;

    public BankCard(User user) {
        this.number = generateBankCardNumber();
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(int intAmount) {
        this.amount = new BigDecimal(intAmount);
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "number='" + number + '\'' +
                ", user=" + user.getName() + " " + user.getSurname() +
                ", amount=" + amount +
                '}';
    }
}

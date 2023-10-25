package com.epam.jmp.dmytro_hontar.dto.bankcard;

import static com.epam.jmp.dmytro_hontar.dto.bankcard.card_number_generator.BankCardNumberGenerator.generateBankCardNumber;

import com.epam.jmp.dmytro_hontar.dto.User;

import java.math.BigDecimal;

public abstract class BankCard {
    private final String number;
    private final User user;
    private BigDecimal amount;

    public BankCard(User user) {
        this.number = generateBankCardNumber();
        this.user = user;
        this.amount = new BigDecimal(0);
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}

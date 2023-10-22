package com.epam.jmp.dmytro_hontar.dto.bankcard;

import com.epam.jmp.dmytro_hontar.dto.User;

import java.math.BigDecimal;

public class CreditBankCard extends BankCard{
    private BigDecimal creditLimit;

    public CreditBankCard(User user) {
        super(user);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}

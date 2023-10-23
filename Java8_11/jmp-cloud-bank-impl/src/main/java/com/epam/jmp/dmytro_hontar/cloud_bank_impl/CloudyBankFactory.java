package com.epam.jmp.dmytro_hontar.cloud_bank_impl;

import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.CreditBankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.DebitBankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

public class CloudyBankFactory implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        if (user == null) return null;
        switch (cardType) {
            case CREDIT:
                return new CreditBankCard(user);
            case DEBIT:
                return new DebitBankCard(user);
            default:
                throw new IllegalArgumentException("Unknown card type" + cardType);
        }
    }
}

package com.epam.jmp.dmytro_hontar.cloud_bank_impl;

import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.CreditBankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.DeditBankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

public class MyUglyBank implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        switch (cardType) {
            case CREDIT:
                return new CreditBankCard(user);
            case DEBIT:
                return new DeditBankCard(user);
            default:
                return null;
        }
    }
}

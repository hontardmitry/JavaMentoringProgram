package com.epam.jmp.dmytro_hontar.bank_api;

import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

public interface Bank {
    public BankCard createBankCard(User user, BankCardType cardType);
}

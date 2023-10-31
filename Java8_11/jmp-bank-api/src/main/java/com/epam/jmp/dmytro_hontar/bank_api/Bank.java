package com.epam.jmp.dmytro_hontar.bank_api;

import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

import java.util.List;

public interface Bank {
    List<BankCard> createBankCards(List<User> users, BankCardType cardType);
}

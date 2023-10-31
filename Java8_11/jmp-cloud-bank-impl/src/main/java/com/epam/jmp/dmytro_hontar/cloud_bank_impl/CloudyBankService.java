package com.epam.jmp.dmytro_hontar.cloud_bank_impl;

import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

import java.util.List;
import java.util.stream.Collectors;

public class CloudyBankService implements Bank {

    @Override
    public List<BankCard> createBankCards(List<User> users, BankCardType cardType) {
        return users.stream()
                .map(cardType::createCard)
                .collect(Collectors.toList());
    }
}

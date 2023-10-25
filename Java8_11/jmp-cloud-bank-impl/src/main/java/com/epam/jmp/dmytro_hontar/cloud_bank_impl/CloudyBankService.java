package com.epam.jmp.dmytro_hontar.cloud_bank_impl;

import static com.epam.jmp.dmytro_hontar.dto.enums.BankCardType.CREDIT;

import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.CreditBankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.DebitBankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CloudyBankService implements Bank {
    @Override
    public List<BankCard> createBankCards(List<User> users, BankCardType cardType) {
        return users.stream()
                .map((CREDIT.equals(cardType)? CreditBankCard::new : DebitBankCard::new))
                .collect(Collectors.toList());
    }
}

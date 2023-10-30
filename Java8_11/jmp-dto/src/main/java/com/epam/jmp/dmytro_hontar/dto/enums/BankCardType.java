package com.epam.jmp.dmytro_hontar.dto.enums;

import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.CreditBankCard;
import com.epam.jmp.dmytro_hontar.dto.bankcard.DebitBankCard;

import java.util.function.Function;

public enum BankCardType {
    CREDIT(CreditBankCard::new),
    DEBIT(DebitBankCard::new);

    BankCardType(Function<User, BankCard> cardFunction) {
        this.cardFunction = cardFunction;
    }

    private final Function<User, BankCard> cardFunction;

    public BankCard createCard(User user) {
        return cardFunction.apply(user);
    }
}

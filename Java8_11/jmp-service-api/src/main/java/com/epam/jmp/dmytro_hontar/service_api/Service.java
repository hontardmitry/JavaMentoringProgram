package com.epam.jmp.dmytro_hontar.service_api;

import com.epam.jmp.dmytro_hontar.dto.Subscription;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;

import java.util.List;
import java.util.Optional;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();
}

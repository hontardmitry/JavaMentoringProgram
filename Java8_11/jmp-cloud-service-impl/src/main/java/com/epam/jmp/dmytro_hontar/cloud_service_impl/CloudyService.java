package com.epam.jmp.dmytro_hontar.cloud_service_impl;

import com.epam.jmp.dmytro_hontar.dto.Subscription;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.service_api.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CloudyService implements Service {

    List<User> users = new ArrayList<>();
    List<Subscription> subscriptions = new ArrayList<>();
    @Override
    public void subscribe(BankCard bankCard) {
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                .filter(subscription -> cardNumber.equals(subscription.getBankCard()))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}

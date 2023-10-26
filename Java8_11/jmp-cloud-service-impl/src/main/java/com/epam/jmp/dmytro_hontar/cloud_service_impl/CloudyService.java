package com.epam.jmp.dmytro_hontar.cloud_service_impl;

import com.epam.jmp.dmytro_hontar.cloud_service_impl.exceptions.InvalidCardNumberException;
import com.epam.jmp.dmytro_hontar.dto.Subscription;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.service_api.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CloudyService implements Service {
    public CloudyService(List<User> users) {
        this.users = users;
    }

    private final List<User> users;
    private List<Subscription> subscriptions = new ArrayList<>();
    @Override
    public void subscribe(BankCard bankCard) {
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Subscription getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                .filter(subscription -> cardNumber.equals(subscription.getBankCard()))
                .findFirst()
                .orElseThrow(InvalidCardNumberException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream().collect(Collectors.toUnmodifiableList());
    }
}

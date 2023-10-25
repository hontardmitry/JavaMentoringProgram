package com.epam.jmp.dmytro_hontar.application.datagenerator;


import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

public class DataGenerator {
    private final Random random = new Random();
    private final List<User> users = new ArrayList<>();
    private List<BankCard> debitCards;
    private List<BankCard> creditCards;

    private final Iterable<Bank> services = ServiceLoader.load(Bank.class);
    private final Bank bankService = services.iterator().next();

    public void generateData(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            String firstName = "User" + (i + 1);
            String lastName = "Lastname" + (i + 1);
            int year = 1970 + random.nextInt(50);
            int month = 1 + random.nextInt(12);
            int day = 1 + random.nextInt(28);

            LocalDate birthDate = LocalDate.of(year, month, day);
            User user = new User(firstName, lastName, birthDate);
            users.add(user);
        }
        debitCards = bankService.createBankCards(users, BankCardType.DEBIT);
        creditCards = bankService.createBankCards(users, BankCardType.CREDIT);
    }

    public List<User> getUsers() {
        return users;
    }

}

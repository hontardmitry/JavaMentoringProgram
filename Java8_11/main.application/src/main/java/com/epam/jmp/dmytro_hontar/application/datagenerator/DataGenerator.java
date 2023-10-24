package com.epam.jmp.dmytro_hontar.application.datagenerator;

import static com.epam.jmp.dmytro_hontar.dto.enums.BankCardType.CREDIT;
import static com.epam.jmp.dmytro_hontar.dto.enums.BankCardType.DEBIT;


import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.cloud_service_impl.CloudyService;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

public class DataGenerator {
    private static final int NUMBER_OF_USERS = 50;
    private final Random random = new Random();
    private final CloudyService cloudyService = new CloudyService();
    private List<User> users = new ArrayList<>();
    private List<BankCard> bankCards = new ArrayList<>();

    private Iterable<Bank> services = ServiceLoader.load(Bank.class);
    private final Bank bankService = services.iterator().next();

    public void generateData() {
        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            String firstName = "User" + (i + 1);
            String lastName = "Lastname" + (i + 1);
            int year = 1970 + random.nextInt(50);
            int month = 1 + random.nextInt(12);
            int day = 1 + random.nextInt(28);

            LocalDate birthDate = LocalDate.of(year, month, day);
            User user = new User(firstName, lastName, birthDate);
            users.add(user);

            BankCard bankCard;
            if (i < NUMBER_OF_USERS / 2) {
                bankCard = bankService.createBankCard(user, DEBIT);
            } else {
                bankCard = bankService.createBankCard(user, CREDIT);
            }
            bankCards.add(bankCard);
            cloudyService.subscribe(bankCard);
        }
    }
}

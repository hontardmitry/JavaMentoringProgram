package com.epam.jmp.dmytro_hontar.application;

import com.epam.jmp.dmytro_hontar.application.datagenerator.DataGenerator;
import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.cloud_service_impl.CloudyService;
import com.epam.jmp.dmytro_hontar.dto.Subscription;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;
import com.epam.jmp.dmytro_hontar.dto.enums.BankCardType;
import com.epam.jmp.dmytro_hontar.service_api.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class BankApplication {
    private static final String BREAK_LINE = "--------------------------------------------------------------";
    private static final int NUMBER_OF_USERS = 100;
    private static final int AMOUNT_LIMIT = 10000;
    private static final int KIDS_AMOUNT_LIMIT = 100;
    private static final int CREDIT_LIMIT = 1000;

    public static void main(String[] args) {
        var services = ServiceLoader.load(Bank.class);
        var bankService = services.iterator().next();

        var random = new Random();
        var dataGenerator = new DataGenerator();
        dataGenerator.generateData(NUMBER_OF_USERS);
        var cloudService = new CloudyService(dataGenerator.getUsers());

        var users = cloudService.getAllUsers();

        System.out.println(BREAK_LINE + "\nUser example:\n" + users.stream().findAny().orElseThrow());
        System.out.println("Total number of users: " + users.size());

        var usersPayable = users.stream().filter(Service::isPayableUser).collect(Collectors.toUnmodifiableList());
        System.out.println("Number of payable users is: " + usersPayable.size());
        printWithBreakLine("Average all users age is: " + cloudService.getAverageUsersAge());

        var debitCards = bankService.createBankCards(users, BankCardType.DEBIT);
        System.out.println(debitCards.size() + " debit cards created");


        var creditCards = bankService.createBankCards(usersPayable, BankCardType.CREDIT);
        printWithBreakLine(creditCards.size() + " credit cards created");

        debitCards.forEach(dc -> {
            dc.setAmount(Service.isPayableUser(dc.getUser()) ? random.nextInt(AMOUNT_LIMIT) : KIDS_AMOUNT_LIMIT);
            cloudService.subscribe(dc);
        });

        creditCards.forEach(cc -> cc.setAmount(random.nextInt(AMOUNT_LIMIT) + CREDIT_LIMIT));
        String neededCardNumber = debitCards.stream()
                .filter(cc -> new BigDecimal(5000).compareTo(cc.getAmount()) > 0)
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .getNumber();

        Subscription subscription = cloudService.getSubscriptionByBankCardNumber(neededCardNumber);
        printWithBreakLine("Searched subscription with more than 5000 amount is: " +
                subscription.toString());


        BigDecimal totalAmountOnDebitCards = debitCards.stream()
                .filter(card -> usersPayable.contains(card.getUser()))
                .map(BankCard::getAmount)
                .reduce(BigDecimal::add)
                .orElseThrow(IllegalArgumentException::new);

        System.out.println("Total amount on debit cards is: " + totalAmountOnDebitCards + " EUR");

        BigDecimal maxAmountOnCreditCards = creditCards.stream()
                .map(BankCard::getAmount)
                .max(Comparator.naturalOrder())
                .orElseThrow(IllegalArgumentException::new);

        printWithBreakLine("Max amount on credit cards is: " + maxAmountOnCreditCards + " EUR");

        try {
            users.add(new User("Apca", "Dripca",
                    LocalDate.now().minus(30, ChronoUnit.YEARS)));
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    private static void printWithBreakLine(String textToPrint) {
        System.out.println(textToPrint);
        System.out.println(BREAK_LINE);
    }

}

package com.epam.jmp.dmytro_hontar.application;

import com.epam.jmp.dmytro_hontar.application.datagenerator.DataGenerator;
import com.epam.jmp.dmytro_hontar.bank_api.Bank;
import com.epam.jmp.dmytro_hontar.cloud_service_impl.CloudyService;
import com.epam.jmp.dmytro_hontar.dto.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.ServiceLoader;

public class BankApplication {
    private static final int NUMBER_OF_USERS = 100;
    private static final BigDecimal CREDIT_LIMIT= new BigDecimal(600);
    private static final BigDecimal KIDS_CREDIT_LIMIT= new BigDecimal(100);
    private final Iterable<Bank> services = ServiceLoader.load(Bank.class);
    private final Bank bankService = services.iterator().next();
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateData(NUMBER_OF_USERS);

        CloudyService cloudService = new CloudyService(dataGenerator.getUsers());


    }
}

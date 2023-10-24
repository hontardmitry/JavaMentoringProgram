package com.epam.jmp.dmytro_hontar.dto.bankcard.card_number_generator;

import java.util.Random;

public class BankCardNumberGenerator {
    private BankCardNumberGenerator() {
    }
    private static final String CARD_PREFIX = "4"; // Префикс для Visa-карт (
    private static final int CARD_NUMBER_LENGTH = 16; // Длина номера карты
    private static final Random random = new Random();

    public static String generateBankCardNumber() {
        StringBuilder cardNumber = new StringBuilder(CARD_PREFIX);

        for (int i = CARD_PREFIX.length(); i < CARD_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }
}

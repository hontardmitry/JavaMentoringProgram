package com.epam.jmp.dmytro_hontar.dto.bankcard.card_number_generator;

import java.util.Random;

public class BankCardNumberGenerator {
    private BankCardNumberGenerator() {
    }
    private static final String CARD_PREFIX = "4"; // Префикс для Visa-карт (можете изменить на другой)
    private static final int CARD_NUMBER_LENGTH = 16; // Длина номера карты
    private static final Random random = new Random();

    public static String generateBankCardNumber() {
        StringBuilder cardNumber = new StringBuilder(CARD_PREFIX);

        // Генерируем оставшуюся часть номера карты случайными цифрами
        for (int i = CARD_PREFIX.length(); i < CARD_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10); // Генерируем случайную цифру от 0 до 9
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }
}

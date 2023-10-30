package com.epam.jmp.dmytro_hontar.service_api;

import com.epam.jmp.dmytro_hontar.dto.Subscription;
import com.epam.jmp.dmytro_hontar.dto.User;
import com.epam.jmp.dmytro_hontar.dto.bankcard.BankCard;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public interface Service {
    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToLong(Service::getUserAge)
                .average()
                .orElseThrow(IllegalArgumentException::new);
    }

    static boolean isPayableUser(User user) {
        return getUserAge(user) >= 18;
    }

    private static Long getUserAge(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());
    }

    void subscribe(BankCard bankCard);

    Subscription getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

}

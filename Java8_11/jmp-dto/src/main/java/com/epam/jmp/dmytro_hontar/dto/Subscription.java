package com.epam.jmp.dmytro_hontar.dto;

import java.time.LocalDate;

public class Subscription {
    private String bankCard;
    private LocalDate startDate;

    public Subscription(String bankCard, LocalDate startDate) {
        this.bankCard = bankCard;
        this.startDate = startDate;
    }

    public String getBankCard() {
        return bankCard;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "bankCard='" + bankCard + '\'' +
                ", startDate=" + startDate +
                '}';
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}

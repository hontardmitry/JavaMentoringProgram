package com.epam.jmp.dmytro_hontar.application.datagenerator;


import com.epam.jmp.dmytro_hontar.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private final Random random = new Random();
    private final List<User> users = new ArrayList<>();

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

    }

    public List<User> getUsers() {
        return users;
    }
}

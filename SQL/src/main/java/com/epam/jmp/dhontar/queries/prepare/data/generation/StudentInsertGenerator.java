package com.epam.jmp.dhontar.queries.prepare.data.generation;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StudentInsertGenerator {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();
        StringBuilder values = new StringBuilder();

        for (int i = 0; i < 1000; i++) {
            String name = random.nextDouble() > 0.02 ? faker.name().firstName() : null;
            String surname = generateRandomSurname(random);
            LocalDate dob = faker.date().birthday(10, 90).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String primarySkill = faker.lorem().word();
            String createdDatetime = faker.date().past(365 * 2, TimeUnit.DAYS).toString();
            String updatedDatetime = faker.date().past(365 * 2, TimeUnit.DAYS).toString();

            values.append(String.format("('%s', '%s', '%s', '%s', '%s', '%s'),", name, surname, dob, primarySkill,
                    createdDatetime, updatedDatetime));
        }

        String insertStatement = "INSERT INTO middle.student (name, surname, dob, primary_skill, created_datetime, " +
                "updated_datetime) VALUES " + values.substring(0, values.length() - 1) + ";";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("insert_1000_student.sql"))) {
            writer.write(insertStatement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomSurname(Random random) {
        int length = random.nextInt(50);
        if (length == 0) {
            return null;
        }
        String surname = random.ints(48, 122)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        if (random.nextDouble() < 0.05) {
            surname += ".";
        }
        return surname;
    }
}
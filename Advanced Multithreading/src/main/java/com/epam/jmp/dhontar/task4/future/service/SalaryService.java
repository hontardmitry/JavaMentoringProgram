package com.epam.jmp.dhontar.task4.future.service;

import com.epam.jmp.dhontar.task4.future.dto.Salary;
import com.epam.jmp.dhontar.task4.future.dto.data.DataGenerator;

import java.util.List;

public class SalaryService {

    protected static final List<Salary> SALARIES = DataGenerator.generateSalaries(150);

    public Double getSalary(int employeeId) {
//        CompletableFuture<Double> doubleCompletableFuture = new CompletableFuture<>();
//        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Thread.sleep(10);
//                doubleCompletableFuture.complete(
                return
                        SALARIES.stream()
                        .filter(s -> employeeId == (s.getEmployeeId()))
                        .findFirst()
                        .map(Salary::getSalary)
                        .orElse(0d);
//    );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            return null;
//        });
//        return doubleCompletableFuture;
        return null;
    }
}

package com.epam.jmp.dhontar.task4.future.service;

import com.epam.jmp.dhontar.task4.future.dto.Employee;
import com.epam.jmp.dhontar.task4.future.data.DataGenerator;

import java.util.List;

public class EmployeeService {

    public List<Employee> getHiredEmployees() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return DataGenerator.generateEmployees(150);
    }
}

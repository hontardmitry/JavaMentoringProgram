package com.epam.jmp.dhontar.future.service;

import com.epam.jmp.dhontar.future.dto.Employee;
import com.epam.jmp.dhontar.future.dto.data.DataGenerator;

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

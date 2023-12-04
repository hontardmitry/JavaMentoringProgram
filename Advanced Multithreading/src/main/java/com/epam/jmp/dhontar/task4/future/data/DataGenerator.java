package com.epam.jmp.dhontar.task4.future.data;

import com.epam.jmp.dhontar.task4.future.dto.Employee;
import com.epam.jmp.dhontar.task4.future.dto.Salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final String[] NAMES = {"John", "Mary", "David", "Sarah", "Michael", "Emily", "James", "Emma", "William", "Olivia"};
    private static final String[] POSITIONS = {"Manager", "Developer", "Designer", "Salesperson", "Accountant"};
    private static final Random RANDOM = new Random();

    public static List<Employee> generateEmployees(int count) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setName(NAMES[RANDOM.nextInt(NAMES.length)]);
            employee.setPosition(POSITIONS[RANDOM.nextInt(POSITIONS.length)]);
            employees.add(employee);
        }
        return employees;
    }

    public static List<Salary> generateSalaries(int count) {
        List<Salary> salaries = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Salary salary = new Salary(i, 1500d + RANDOM.nextInt(5500));
            salaries.add(salary);
        }
        return salaries;
    }

}

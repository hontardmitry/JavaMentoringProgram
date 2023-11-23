package com.epam.jmp.dhontar.future.dto;

public class Salary {
    private final int employeeId;
    private final double salary;

    public Salary(int employeeId, double salary) {
        this.employeeId = employeeId;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getSalary() {
        return salary;
    }
}

package com.epam.jmp.dhontar.patterns.lynda.behavioral.visitor;

import java.util.ArrayList;

public class StaffList implements Employee {

    private ArrayList<Employee> salaries = new ArrayList<>();

    public StaffList() {
        Manager manager = new Manager();
        SalesPerson salesPerson1 = new SalesPerson();
        SalesPerson salesPerson2 = new SalesPerson();

        salaries.add(manager);
        salaries.add(salesPerson1);
        salaries.add(salesPerson2);
    }

    @Override
    public int getSalary() {
        return salaries.stream().mapToInt(Employee::getSalary).sum();
    }

    @Override
    public void accept(Visitor visitor) {
        salaries.forEach(e -> e.accept(visitor));
        visitor.visit(this);
    }
}

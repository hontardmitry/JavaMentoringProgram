package com.epam.jmp.dhontar.lynda.behavioral.visitor;

public class SalesPerson implements Employee{

    private int salary = 50000;

    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

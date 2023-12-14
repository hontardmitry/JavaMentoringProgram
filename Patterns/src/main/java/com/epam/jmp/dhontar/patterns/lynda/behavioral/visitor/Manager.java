package com.epam.jmp.dhontar.patterns.lynda.behavioral.visitor;

public class Manager implements Employee{

    private int salary = 60000;

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

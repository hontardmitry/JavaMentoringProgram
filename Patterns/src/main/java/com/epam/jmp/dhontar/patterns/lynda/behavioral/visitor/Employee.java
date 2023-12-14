package com.epam.jmp.dhontar.patterns.lynda.behavioral.visitor;

public interface Employee {

    int getSalary();

    void accept(Visitor visitor);
}

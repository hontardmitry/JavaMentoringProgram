package com.epam.jmp.dhontar.lynda.behavioral.visitor;

public interface Employee {

    int getSalary();

    void accept(Visitor visitor);
}

package com.epam.jmp.dhontar.patterns.lynda.behavioral.visitor;

public interface Visitor {

    void visit(Manager manager);
    void visit(SalesPerson salesPerson);
    void visit(StaffList staffList);
}

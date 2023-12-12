package com.epam.jmp.dhontar.lynda.behavioral.visitor;

public class SalaryAdjustmentVisitor implements Visitor {
    @Override
    public void visit(Manager manager) {
        manager.setSalary(65000);
    }

    @Override
    public void visit(SalesPerson salesPerson) {
        salesPerson.setSalary(55000);
    }

    @Override
    public void visit(StaffList staffList) {
        System.out.println("Salaries were raised for all employees");
    }
}

package com.epam.jmp.dhontar.lynda.behavioral.visitor;

public class Client {

    public static void main(String[] args) {
        StaffList staffList = new StaffList();
        System.out.printf("Total amount paid to staff: %d", staffList.getSalary());
        staffList.accept(new SalaryAdjustmentVisitor());

        System.out.printf("New total amount paid to staff: %d", staffList.getSalary());
    }
}

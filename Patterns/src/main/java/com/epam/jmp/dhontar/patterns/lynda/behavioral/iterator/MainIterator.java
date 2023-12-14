package com.epam.jmp.dhontar.patterns.lynda.behavioral.iterator;

public class MainIterator {

    public static void main(String[] args) {

        Employee zak = new Employee("Zak");
        Employee sarah = new Employee("Sarah");
        Employee anna = new Employee("Anna");

        var staffList = new StaffList(zak, sarah, anna);
        var staffIterator = new StaffListIterator(staffList);

        while (staffIterator.hasNext()){
            System.out.println(staffIterator.next().getName());
        }
    }
}

package com.epam.jmp.dhontar.lynda.behavioral.iterator;

public class StaffList implements Iterable<Employee>{

    private final Employee[] employees;

    public StaffList(Employee... employees) {
        this.employees = employees;
    }

    public Employee[] getEmployees(){
        return employees;
    }

    @Override
    public StaffListIterator iterator() {
        return new StaffListIterator(this);
    }
}

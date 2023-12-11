package com.epam.jmp.dhontar.lynda.behavioral.iterator;

import java.util.Iterator;

public class StaffListIterator implements Iterator<Employee> {

    private final StaffList staffList;
    private int index;


    public StaffListIterator(StaffList staffList) {
        this.staffList = staffList;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < staffList.getEmployees().length;
    }

    @Override
    public Employee next() {
        return (hasNext()) ? staffList.getEmployees()[index++] : null;
    }
}

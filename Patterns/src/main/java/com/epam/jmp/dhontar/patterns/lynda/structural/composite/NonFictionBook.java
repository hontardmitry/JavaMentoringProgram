package com.epam.jmp.dhontar.patterns.lynda.structural.composite;

public class NonFictionBook implements Books {

    private final String name;
    private boolean checkedOut;

    public NonFictionBook(String name) {
        this.name = name;
        checkedOut = false;
    }

    @Override
    public void checkout() {
        if (!checkedOut) {
            System.out.printf("Checking out %s\n", name);
            checkedOut = true;
        } else {
            System.out.printf("%s is already checked out\n", name);
        }
    }

    @Override
    public void returnBook() {
        if (checkedOut) {
            System.out.printf("Returning %s\n", name);
            checkedOut = false;
        } else {
            System.out.printf("%s is not checked out\n", name);
        }
    }
}

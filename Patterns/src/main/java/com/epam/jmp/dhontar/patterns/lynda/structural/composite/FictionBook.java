package com.epam.jmp.dhontar.patterns.lynda.structural.composite;

public class FictionBook implements Books {

    private final String name;
    private final boolean iaAPlay;
    private boolean checkedOut;

    public FictionBook(String name, boolean iaAPlay) {
        this.name = name;
        this.iaAPlay = iaAPlay;
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

package com.epam.jmp.dhontar.patterns.lynda.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class BookCollection implements Books {

    private final List<Books> books = new ArrayList<>();


    public void addBook(Books books) {
        this.books.add(books);
    }

    public void checkout() {
        books.forEach(Books::checkout);
    }

    public void returnBook() {
        books.forEach(Books::returnBook);
    }
}

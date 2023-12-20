package com.epam.jmp.dhontar.patterns.lynda.structural.composite;

public class LibraryClient {

    public static void main(String[] args) {

        NonFictionBook nonFictionBook = new NonFictionBook("A Breif History of Time");
        FictionBook fictionBookOne = new FictionBook("Hamlet", true);
        FictionBook fictionBookTwo = new FictionBook("The Great Gatsby", false);

        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(nonFictionBook);
        bookCollection.addBook(fictionBookOne);
        bookCollection.addBook(fictionBookTwo);

        checkoutReading(nonFictionBook);
        checkoutReading(fictionBookOne);
        checkoutReading(bookCollection);

        returnReading(nonFictionBook);
        returnReading(fictionBookOne);
        returnReading(bookCollection);
    }

    public static void checkoutReading(Books books) {
        books.checkout();
    }

    public static void returnReading(Books books) {
        books.returnBook();
    }

}

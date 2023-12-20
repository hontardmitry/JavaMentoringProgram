package com.epam.jmp.dhontar.patterns.lynda.behavioral.memento;

public class DocumentViewer {

    private static final TextDocument textDocument = new TextDocument();

    public static void main(String[] args) {
        textDocument.write("hello");
        textDocument.save();
        textDocument.print();
        textDocument.write(" world");
        textDocument.print();
        textDocument.undo();
        textDocument.print();
    }

}

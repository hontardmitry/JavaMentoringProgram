package com.epam.jmp.dhontar.patterns.lynda.behavioral.command;

//Receiver
public class Document {

    public void save(){
        System.out.println("Saving documents...");
    }

    public void print(){
        System.out.println("Printing document...");
    }
}

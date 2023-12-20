package com.epam.jmp.dhontar.patterns.lynda.behavioral.interpreter;

public class MainInterpreter {
    public static void main(String[] args) {
        String context = "_Int";
        var firstLetterNotUnderscore = new FirstLetterNotUnderscore();

        context = firstLetterNotUnderscore.interpret(context);
        System.out.println(context);
    }



}

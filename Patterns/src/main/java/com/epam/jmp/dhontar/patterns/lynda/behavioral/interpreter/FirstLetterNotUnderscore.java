package com.epam.jmp.dhontar.patterns.lynda.behavioral.interpreter;

public class FirstLetterNotUnderscore implements Expression {

    private final FirstLetterIsLowerCase firstLetterIsLowerCase =
            new FirstLetterIsLowerCase();
    @Override
    public String interpret(String context) {
        context = (context.startsWith("_")) ? context.substring(1) : context;
        return firstLetterIsLowerCase.interpret(context);
    }
}

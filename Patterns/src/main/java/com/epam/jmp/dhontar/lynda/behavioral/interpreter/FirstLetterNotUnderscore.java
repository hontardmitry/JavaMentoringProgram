package com.epam.jmp.dhontar.lynda.behavioral.interpreter;

public class FirstLetterNotUnderscore implements Expression {

    private FirstLetterIsLowerCase firstLetterIsLowerCase =
            new FirstLetterIsLowerCase();
    @Override
    public String interpret(String context) {
        context = (context.startsWith("_")) ? context.substring(1) : context;
        return firstLetterIsLowerCase.interpret(context);
    }
}

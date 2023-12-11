package com.epam.jmp.dhontar.lynda.behavioral.interpreter;

public class FirstLetterIsLowerCase implements Expression {

    private NameNotPrimitiveType nameNotPrimitiveType =
            new NameNotPrimitiveType();
    @Override
    public String interpret(String context) {
        context = context.substring(0, 1).toLowerCase() +
                context.substring(1);
        return nameNotPrimitiveType.interpret(context);
    }
}

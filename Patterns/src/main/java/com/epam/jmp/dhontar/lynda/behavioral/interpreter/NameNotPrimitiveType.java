package com.epam.jmp.dhontar.lynda.behavioral.interpreter;

import java.util.List;

public class NameNotPrimitiveType implements Expression {

    @Override
    public String interpret(String context) {
        var primitiveNames = List.of("int", "boolean", "double");
        return (primitiveNames.contains(context)) ? context + "1" : context;
    }
}

package com.epam.jmp.dhontar.patterns.lynda.behavioral.command;

//Invoker
public class Button {

    public void click(Command command){
        command.execute();
    }
}

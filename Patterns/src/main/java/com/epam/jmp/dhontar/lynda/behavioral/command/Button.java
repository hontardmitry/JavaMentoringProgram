package com.epam.jmp.dhontar.lynda.behavioral.command;

//Invoker
public class Button {

    public void click(Command command){
        command.execute();
    }
}

package com.epam.jmp.dhontar.lynda.behavioral.command;

public class Button {

    public void click(Command command){
        command.execute();
    }
}

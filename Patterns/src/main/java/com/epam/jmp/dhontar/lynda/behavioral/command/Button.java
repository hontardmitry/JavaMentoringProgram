package com.epam.jmp.dhontar.lynda.behavioral.command;

public class Button {
    private String text;

    public Button(String text) {
        this.text = text;
    }

    public void click(Command command){
        command.execute();
    }
}

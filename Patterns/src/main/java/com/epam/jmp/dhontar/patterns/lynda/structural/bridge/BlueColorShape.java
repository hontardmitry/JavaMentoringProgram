package com.epam.jmp.dhontar.patterns.lynda.structural.bridge;

import java.awt.Color;
import java.awt.Graphics;

public class BlueColorShape implements ColorShape{

    @Override
    public void setColor(Graphics graphics) {
        graphics.setColor(Color.BLUE);
    }
}

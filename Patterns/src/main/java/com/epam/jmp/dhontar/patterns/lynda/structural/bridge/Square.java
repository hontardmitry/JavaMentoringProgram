package com.epam.jmp.dhontar.patterns.lynda.structural.bridge;

import java.awt.Graphics;

public class Square {

    ColorShape colorShape;

    public Square(ColorShape colorShape) {
        this.colorShape = colorShape;
    }

    public void draw(Graphics graphics) {
        colorShape.setColor(graphics);
        graphics.fillRect(5, 15, 50, 50);
    }
}

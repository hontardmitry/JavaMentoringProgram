package com.epam.jmp.dhontar.patterns.lynda.structural.bridge;

import java.awt.Graphics;

public class Circle {

    ColorShape colorShape;

    public Circle(ColorShape colorShape) {
        this.colorShape = colorShape;
    }

    public void draw(Graphics graphics) {
        colorShape.setColor(graphics);
        graphics.fillOval(75, 15, 50, 50);
    }
}

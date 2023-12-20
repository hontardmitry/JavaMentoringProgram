package com.epam.jmp.dhontar.patterns.lynda.structural.bridge;

import java.awt.Graphics;

public class Triangle {

    ColorShape colorShape;

    public Triangle(ColorShape colorShape) {
        this.colorShape = colorShape;
    }

    public void draw(Graphics graphics) {
        colorShape.setColor(graphics);
        graphics.fillPolygon(new int[]{140, 170, 200}, new int[]{65, 15, 65}, 3);
    }
}

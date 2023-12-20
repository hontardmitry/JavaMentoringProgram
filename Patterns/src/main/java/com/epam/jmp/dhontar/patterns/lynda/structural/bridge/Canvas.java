package com.epam.jmp.dhontar.patterns.lynda.structural.bridge;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;

public class Canvas extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.add(new Canvas());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        Square square = new Square(new BlueColorShape());
        square.draw(graphics);

        Circle circle = new Circle(new RedColorShape());
        circle.draw(graphics);

        Triangle triangle = new Triangle(new GreenColorShape());
        triangle.draw(graphics);
    }
}

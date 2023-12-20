package com.epam.jmp.dhontar.patterns.lynda.structural.decorator;

public class Main {

    public static void main(String[] args) {
        order(new PizzaMargherita());
        order(new PizzaHawaiian());
        order(new ExtraCheese(new PizzaHawaiian()));
        order(new PizzaPepperoni());
    }

    public static void order(Pizza pizza) {
        System.out.printf("You have ordered a %s pizza. The toppings are %s\n", pizza.getName(), pizza.getTopping());
    }
}

package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

import java.util.ArrayList;
import java.util.List;

public class ChocolateWhilte extends Candy {

    @Override
    List<Candy> makeCandyPackage(int quantity) {
        List<Candy> chocolatePackage = new ArrayList<>();
        for (var i = 1; i <= quantity; i++){
            var chocolate = new ChocolateWhilte();
            chocolatePackage.add(chocolate);
        }
        System.out.println("One package of " + quantity + " white chocolates have been made.");
        return chocolatePackage;
    }
}

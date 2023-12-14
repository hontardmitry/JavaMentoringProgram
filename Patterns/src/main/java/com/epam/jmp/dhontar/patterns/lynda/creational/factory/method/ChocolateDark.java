package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

import java.util.ArrayList;
import java.util.List;

public class ChocolateDark extends Candy {

    @Override
    List<Candy> makeCandyPackage(int quantity) {
        List<Candy> chocolatePackage = new ArrayList<>();
        for (var i = 1; i <= quantity; i++){
            var chocolate = new ChocolateDark();
            chocolatePackage.add(chocolate);
        }
        System.out.println("One package of " + quantity + " dark chocolates have been made.");
        return chocolatePackage;
    }
}

package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

import java.util.ArrayList;
import java.util.List;

public class HardCandyCane extends Candy {

    @Override
    List<Candy> makeCandyPackage(int quantity) {
        List<Candy> hardCandyPackage = new ArrayList<>();
        for (var i = 1; i <= quantity; i++){
            var hardCandy = new HardCandyCane();
            hardCandyPackage.add(hardCandy);
        }
        System.out.println(quantity + " packages of 10 candy canes have been made.");
        return hardCandyPackage;
    }
}

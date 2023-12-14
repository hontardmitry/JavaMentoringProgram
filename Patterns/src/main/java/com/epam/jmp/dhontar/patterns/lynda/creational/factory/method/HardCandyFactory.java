package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

import java.util.List;

public class HardCandyFactory extends CandyFactory{

    public Candy getCandy(String type) {
        switch (type) {
            case "candycane":
                return new HardCandyCane();
            case "lollipop":
                return new HardCandyLollipop();
            case "peppermint":
                return new HardCandyPeppermint();
            default:
                return null;
        }
    }

    public List<Candy> getCandyPackage(int quantity, String type) {
        var candy = getCandy(type);
        if (quantity % 10 != 0) {
            System.out.println("Hard candy must be packaged in multiplies of 10.");
            return null;
        }
        assert candy != null;
        return candy.makeCandyPackage(quantity);
    }
}

package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

import java.util.List;

public class ChocolateFactory extends CandyFactory {

    @Override
    public Candy getCandy(String type) {
        switch (type) {
            case "dark":
                return new ChocolateDark();
            case "white":
                return new ChocolateWhilte();
            case "milk":
                return new ChocolateMilk();
            default:
                return null;
        }
    }

//    public List<Candy> getCandyPackage(int quantity, String type, String section) {
//        var candy = getCandy(type);
//        if ("hard candy".equals(section) && quantity % 10 != 0) {
//            System.out.println("Hard candy must be packaged in multiplies of 10.");
//            return null;
//        }
//        assert candy != null;
//        return candy.makeCandyPackage(quantity);
//    }
}

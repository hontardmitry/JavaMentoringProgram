package com.epam.jmp.dhontar.patterns.lynda.creational.factory.method;

import java.util.List;

public abstract class CandyFactory {

    public abstract Candy getCandy(String type);

    public List<Candy> getCandyPackage(int quantity, String type) {
        var candy = getCandy(type);
        assert candy != null;
        return candy.makeCandyPackage(quantity);
    }
}

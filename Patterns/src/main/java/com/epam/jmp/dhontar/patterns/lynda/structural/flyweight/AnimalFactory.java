package com.epam.jmp.dhontar.patterns.lynda.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    Map<Integer, Animal> animals = new HashMap<>();

    public Animal getAnimal(int type) {
        if (animals.containsKey(type)) {
            return animals.get(type);
        } else {
            Animal animal = (type == 0) ? new Lion() : new Tiger();
            animals.put(type, animal);
            return animal;
        }
    }
}

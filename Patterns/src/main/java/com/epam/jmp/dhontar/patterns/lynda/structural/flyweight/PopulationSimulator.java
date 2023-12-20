package com.epam.jmp.dhontar.patterns.lynda.structural.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PopulationSimulator {

    private static final List<Animal> ANIMALS = new ArrayList<>();
    private static final AnimalFactory ANIMAL_FACTORY = new AnimalFactory();

    public static void main(String[] args) {

        Runnable createAnimals = () -> createRandomAnimal();

        Runnable removeAnimals = () -> removeAnimal();

        var executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(createAnimals, 0, 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(removeAnimals, 5, 5, TimeUnit.SECONDS);
    }

    private static void createRandomAnimal() {
        var random = new Random();
        var randType = random.nextInt(2);
        var animal = ANIMAL_FACTORY.getAnimal(randType);
        var locBound = 1000;
        animal.setLocation(random.nextInt(locBound), random.nextInt(locBound));
        System.out.printf("+++ Creating %s, type: %s, location: %d, %d\n", animal, animal.getAnimalType(),
                animal.getLocation()[0], animal.getLocation()[1]);
        ANIMALS.add(animal);
        System.out.printf("++++++++++++++++Total animals: %d\n", ANIMALS.size());
//        ANIMALS.forEach(animal1 ->
//                System.out.println(animal1.getAnimalType() + ", location:" +
//                        animal1.getLocation()[0] + ", " + animal1.getLocation()[1])
//        );
    }

    private static void removeAnimal() {
        System.out.printf("--- Removing %s\n", ANIMALS.get(0));
        ANIMALS.remove(0);
    }
}

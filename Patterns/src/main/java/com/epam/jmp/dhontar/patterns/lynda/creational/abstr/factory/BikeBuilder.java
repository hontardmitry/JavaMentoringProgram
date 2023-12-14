package com.epam.jmp.dhontar.patterns.lynda.creational.abstr.factory;

public class BikeBuilder {

    public static void main(String[] args) {
//        createBikeWithoutAbstractFactory();
        createBikeWithExtensibleAbstractFactory("road bike");
    }

    public static void createBikeWithoutAbstractFactory() {

        MountainBikeTire mountainBikeTireFront = new MountainBikeTire();
        MountainBikeTire mountainBikeTireBack = new MountainBikeTire();
        MountainBikeHandlebar mountainBikeHandlebar = new MountainBikeHandlebar();

        mountainBikeTireFront.getDescription();
        mountainBikeTireBack.getDescription();
        mountainBikeHandlebar.getDescription();
        System.out.println("_____________________________________________");
    }

    public static void createBikeWithExtensibleAbstractFactory(String type) {

        BikeFactory bikeFactory = FactoryCreator.createFactory(type);

        Tire tireFront = (Tire) bikeFactory.create("tire");
        Tire tireBack = (Tire) bikeFactory.create("tire");
        Handlebar handlebar = (Handlebar) bikeFactory.create("handlebar");

        tireFront.getDescription();
        tireBack.getDescription();
        handlebar.getDescription();
        System.out.println("===============================================");
    }
}

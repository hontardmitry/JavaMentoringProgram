package com.epam.jmp.dhontar.patterns.lynda.creational.proptotype;

public class MainForPrototype {

    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        rabbit.setAge(8);
        rabbit.setOwner("Sally");
        rabbit.setBreed(Rabbit.Breed.MINI_REX);

        Rabbit rabbitClone = rabbit.clone();
        System.out.printf("First rabbit age is %d, owner %s, breed is %s\n", rabbit.getAge(),
                rabbit.getOwner().getName(), rabbit.getBreed());
        System.out.printf("Second rabbit age is %d, owner %s, breed is %s\n", rabbitClone.getAge(),
                rabbitClone.getOwner().getName(), rabbitClone.getBreed());
        rabbitClone.getOwner().setName("Steve");
        System.out.printf("Now first rabbit owner is %s", rabbit.getOwner().getName());
        System.out.printf("Now second rabbit owner is %s", rabbitClone.getOwner().getName());
    }
}

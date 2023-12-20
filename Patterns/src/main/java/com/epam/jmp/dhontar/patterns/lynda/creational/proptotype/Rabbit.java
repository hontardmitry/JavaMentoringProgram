package com.epam.jmp.dhontar.patterns.lynda.creational.proptotype;

public class Rabbit implements Cloneable {

    public enum Breed {
        HIMALAYAN,
        AMERICAN,
        MINI_REX,
        LIONHEAD,
        DUTCH
    }

    private int age;
    private Breed breed;
    private Person owner;

    public Rabbit() {
    }

    public int getAge() {
        return age;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public void setOwner(String name) {
        Person owner = new Person(name);
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }

    @Override
    public Rabbit clone() {
        try {
            Rabbit rabbit = (Rabbit)  super.clone();
            rabbit.owner = owner.clone();
            return rabbit;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }
}

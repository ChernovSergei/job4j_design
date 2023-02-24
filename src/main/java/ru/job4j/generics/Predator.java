package ru.job4j.generics;

public class Predator extends Animal {
    Integer numberOfTushes;

    public Predator(boolean endaged, Integer numberOfTushes) {
        super(endaged);
        this.numberOfTushes = numberOfTushes;
    }
}

package ru.job4j.generics;

public class Tiger extends Predator {
    Integer weight;

    public Tiger(boolean endanged, Integer numberOfTushes, Integer weight) {
        super(endanged, numberOfTushes);
        this.weight = weight;
    }
}

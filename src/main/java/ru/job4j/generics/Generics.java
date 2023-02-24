package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();

        first.add(new Animal(true));
        second.add(new Predator(true, 4));
        third.add(new Tiger(true, 4, 100));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        /*gen.printBoundedWildCard(first);*/
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /*gen.printLowerBoundedWildCard(third);*/
    }

    public <T> void printObject(List<T> list) {
        for (Iterator<T> it = list.iterator(); it.hasNext();) {
            T next = it.next();
            System.out.println("Current element: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Predator next = (Predator) it.next();
            System.out.println("Current element: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Current element: " + next);
        }
    }
}

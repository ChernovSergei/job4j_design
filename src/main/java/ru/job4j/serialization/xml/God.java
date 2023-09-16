package ru.job4j.serialization.xml;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class God {
    private final String name;
    private final int yearOfBirth;
    private final Contact contact;
    private final boolean isImmortal;
    private final String[] children;

    public God(String name, int yearOfBirth, Contact contact, boolean isImmortal, String[] children) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.contact = contact;
        this.isImmortal = isImmortal;
        this.children = children;
    }

    @Override
    public String toString() {
        return "God{"
                + "name=" + name
                + ", year of creation=" + yearOfBirth
                + ", contact =" + children
                + ", immortal =" + isImmortal
                + ", children =" + Arrays.toString(children)
                + '}';
    }
}

package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class Cafeteria {
    private final int foundationYear;
    private final String name;
    private final Contact contact;
    private final boolean isChainBusiness;
    private final String[] employees;

    public Cafeteria(int foundationYear,
                     String name,
                     Contact contact,
                     boolean isChainBusiness,
                     String[] employees) {
        this.foundationYear = foundationYear;
        this.name = name;
        this.contact = contact;
        this.isChainBusiness = isChainBusiness;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Cafeteria{"
                + "foundationYear=" + foundationYear
                + ", name=" + name
                + ", contact=" + contact
                + ", is part of a chain=" + isChainBusiness
                + ", employees:" + Arrays.toString(employees);
    }
}

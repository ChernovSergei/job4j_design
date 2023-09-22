package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class Cafeteria {
    private int foundationYear;
    private String name;
    private Contact contact;
    private boolean isChainBusiness;
    private String[] employees;

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

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isChainBusiness() {
        return isChainBusiness;
    }

    public void setChainBusiness(boolean chainBusiness) {
        isChainBusiness = chainBusiness;
    }

    public String[] getEmployees() {
        return employees;
    }

    public void setEmployees(String[] employees) {
        this.employees = employees;
    }
}

package ru.job4j.iterator.test;

import java.io.*;

public class ObjectStream {
    public static void main(String[] args) {
        Car car = new Car("Company", "Model", 2000);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\projects\\Test\\serialized.dat"));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\projects\\Test\\serialized.dat"))) {
            out.writeObject(car);
            Car deserialized = (Car) in.readObject();
            System.out.println(deserialized.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

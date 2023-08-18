package ru.job4j.io;

import java.io.FileInputStream;

public class EventNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        String[] lines;

        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            if (Integer.parseInt(line) % 2 == 0) {
                System.out.println("Number " + line + " is even");
            } else {
                System.out.println("Number " + line + " is not even");
            }
        }
    }

}

package ru.job4j.iterator.test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, List<String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:/projects/Test/1/2.1/b.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> list = List.of(
                "Я сама",
                "Я сам",
                "Она сама",
                "Он сам",
                "Они сами"
        );
        encoding.writeDataInFile(path, list);
        String s = encoding.readFile(path);
        System.out.println("Data from file: ");
        System.out.println(s);
    }
}

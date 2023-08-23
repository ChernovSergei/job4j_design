package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        char hash = 35;
        char equals = 61;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.putAll(read
                    .lines()
                    .filter(L -> L.charAt(0) != hash && L.charAt(0) != equals)
                    .collect(Collectors.toMap(L -> L.split("=", 2)[0], L -> L.split("=", 2)[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (key == null || key.equals("") || values.get(key) == null || values.get(key).equals("")) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        char equals = 61;
        config.load();
        System.out.println(config.value("hibernate.connection.username"));
        String test = "ad;lk=a;ldkjf=dfla=ad;lj";
        String test2 = "=ad;lfkj";
        String[] splitTest = test.split("=", 2);
        for (String string : splitTest) {
            System.out.println(string);
        }
        System.out.println(test.charAt(0) == equals);
    }
}

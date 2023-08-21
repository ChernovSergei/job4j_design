package ru.job4j.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(S -> Stream.of(S.split(" "))
                            .toArray()[Stream.of(S.split(" ")).toArray().length - 2]
                            .equals("404"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}

package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateArgs(String path, String type) {
        if (path == null) {
            throw new IllegalArgumentException("ERROR: root path is null. Enter the root directory and reboot the app.");
        }
        if (type == null) {
            throw new IllegalArgumentException("ERROR: file type is null. Enter the file type and reboot the app.");
        }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args[0], args[1]);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
}

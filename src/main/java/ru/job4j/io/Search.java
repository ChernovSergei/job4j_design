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

    public static void validateArgs(String[] args) {
       if (args.length != 2) {
           throw new IllegalArgumentException("ERROR! Not enough input parameters. There should be two input parameters.");
       }
       if (!Files.isDirectory(Paths.get(args[0]))) {
           throw new IllegalArgumentException("ERROR! First input isn't directory. First input has to be directory.");
       }
       if (!args[1].startsWith(".") || args[1].length() < 2) {
           throw new IllegalArgumentException("ERROR! Second parameter isn't a file type. Correct second input");
       }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
}

package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> allPaths = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        allPaths.computeIfAbsent(fileProperty, b -> new LinkedList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        return allPaths.values().stream().filter(P -> P.size() > 1).flatMap(Collection::stream).collect(Collectors.toList());
    }
}

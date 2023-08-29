package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> allPathes = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        if (!allPathes.containsKey(fileProperty)) {
            allPathes.put(fileProperty, file.toAbsolutePath());
        } else {
            if (allPathes.get(fileProperty) != null) {
                System.out.println(allPathes.get(fileProperty));
                allPathes.replace(fileProperty, null);
            }
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}

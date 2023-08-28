package ru.job4j.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get("path");
        Path dir = Paths.get("path/path");
        Path file1 = Path.of("path/path/path.txt");
        Path file2 = Path.of("path/path.txt");
        Path path3 = Path.of("path/subPath");
        Files.createDirectories(dir);
        Files.createFile(file1);
        Files.move(file1, file2);
        Files.delete(dir);
        Files.createDirectories(path3);
        DirectoryStream<Path> paths = Files.newDirectoryStream(root);
        paths.forEach(System.out::println);
        System.out.println();
        BasicFileAttributeView attrView = Files.getFileAttributeView(file2, BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();
        System.out.println();
        System.out.println("Basic file: " + attributes.isRegularFile());
        System.out.println("Is direcotry: " + attributes.isDirectory());
        System.out.println("Is link: " + attributes.isSymbolicLink());
        System.out.println("Isn't file/director/link: " + attributes.isOther());
        System.out.println("Date of creation: " + attributes.creationTime());
        System.out.println("Size: " + attributes.size());
        System.out.println("Last access time: " + attributes.lastAccessTime());
        System.out.println("Last change time: " + attributes.lastModifiedTime());
        Files.delete(path3);
        Files.delete(file2);
        Files.delete(root);
    }
}

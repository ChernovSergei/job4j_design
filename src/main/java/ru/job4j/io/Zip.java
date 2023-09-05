package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static ArgsName argsName;
    private String directory;
    private String exclude;
    private String output;

    public static Zip of(String[] args) {
        argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validateInput();
        return zip;
    }

    private void validateInput() {
        directory = argsName.get("d");
        exclude = argsName.get("e");
        output = argsName.get("o");
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException("Error: directory is wrong. There is no such directory");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Error: type error. The exclude object type should start with dot");
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Error: wrong output type. Output should be .zip type");
        }
    }

    public List<Path> getFiles(String startDirectory, String excludeObject) throws IOException {
        Predicate<Path> exclude = Predicate.not((Path p) -> p.toFile().getName().endsWith(excludeObject));
        return Search.search(Paths.get(startDirectory), exclude);
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip myZip = Zip.of(args);
        List<Path> paths = myZip.getFiles(myZip.directory, myZip.exclude);
        myZip.packFiles(paths, Paths.get(myZip.output).toFile());
    }
}

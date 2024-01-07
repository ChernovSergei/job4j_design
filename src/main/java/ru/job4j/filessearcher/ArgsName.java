package ru.job4j.filessearcher;

import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        inputCheck(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] parameter;

        for (int i = 0; i < args.length; i++) {
            parameter = args[i].split("=", 2);
            values.put(parameter[0].substring(1), parameter[1]);
        }
    }

    private void inputCheck(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Error: Key shouldn't equal to zero");
        }
        if (values.get(key) == null) {
            String error = String.format("This key: '%s' is missing", key);
            throw new IllegalArgumentException(error);
        }
    }

    private void argsCheck(String[] args) {

        char equal = 61;
        if (args.length < 1) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }

        for (int i = 0; i < args.length; i++) {

            if (!args[i].contains("=")) {
                String error = String.format("Error: This argument '%s' does not contain an equal sign", args[i]);
                throw new IllegalArgumentException(error);
            }
            if (!args[i].startsWith("-")) {
                String error = String.format("Error: This argument '%s' does not start with a '-' character", args[i]);
                throw new IllegalArgumentException(error);
            }
            if (args[i].startsWith("-") && args[i].charAt(1) == equal) {
                String error = String.format("Error: This argument '%s' does not contain a key", args[i]);
                throw new IllegalArgumentException(error);
            }
            if (args[i].split("=", 2)[1].length() < 1) {
                String error = String.format("Error: This argument '%s' does not contain a value", args[i]);
                throw new IllegalArgumentException(error);
            }

            if (args[i].contains("-d=")) {
                String path = args[i].split("=", 2)[1];
                if (!Files.isDirectory(Paths.get(path))) {
                   String error = String.format("Error: This paths - '%s' is not a directory", path);
                   throw new IllegalArgumentException(error);
                }
            }

            if (args[i].contains("-t=")) {
                String arg = args[i].split("=", 2)[1];
                if (!arg.equals("name") && !arg.equals("mask") && !arg.equals("regex")) {
                   String error = "Error: t parameter should equal to name/mask/regex";
                   throw new IllegalArgumentException(error);
                }
            }

            if (args[i].contains("-o=")) {
                String arg = args[i].split("=", 2)[1];
                if (arg.split("\\.").length != 2) {
                   String error = "Error: o parameter. a destination file should have an extention, like .txt";
                   throw new IllegalArgumentException(error);
                }
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.argsCheck(args);
        names.parse(args);
        return names;
    }

    /*public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-?=="});
        System.out.println(jvm.get("?"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }*/
}

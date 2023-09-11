package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static Path path;
    private static String delimiter;
    private static Path out;
    private static Map<String, Integer> filters = new HashMap<>();

    public static void handle(ArgsName argsName) {
        validateArgs(argsName);
        writeData(readData());
        clearArguments();
    }

    public static void validateArgs(ArgsName argsName) {
        path = Paths.get(argsName.get("path"));
        delimiter = argsName.get("delimiter");
        out = Paths.get(argsName.get("out"));
        String[] filter = argsName.get("filter").split(",");
        for (int i = 0; i < filter.length; i++) {
            filters.put(filter[i], i);
        }

        if (!Files.isDirectory(path.getParent())) {
            String error = "Error: there is nod such directory. Add existing directory and create input .csv file.";
            System.out.println(error);
        }
        if (!Files.exists(path)) {
            String error = "Error: there is no such input file. Create file with input data.";
            throw new IllegalArgumentException(error);
        }
        if (!Files.isDirectory(out.getParent())) {
            String error = "Error: there is nod such directory. Add existing directory.";
            System.out.println(error);
        }
        if (!out.toString().endsWith(".csv")) {
            String error = "Error: output file has to have .csv format. Correct the output file type.";
            throw new IllegalArgumentException(error);
        }
    }

    public static List<String> readData() {
        Map<Integer, Integer> filteredColumns = new HashMap<>();
        List<String> resultRows = new LinkedList<>();
        String[] resultRow = new String[filters.size()];

        try (Scanner rows = new Scanner(new BufferedInputStream(new FileInputStream(path.toString())))) {

            if (rows.hasNextLine()) {
                String[] firstRow = rows.nextLine().split(delimiter);
                for (int i = 0; i < firstRow.length; i++) {
                    if (filters.containsKey(firstRow[i])) {
                        filteredColumns.put(i, filters.get(firstRow[i]));
                        resultRow[filters.get(firstRow[i])] = firstRow[i];
                    }
                }
            }
            resultRows.add(String.join(delimiter, resultRow));
            Arrays.fill(resultRow, null);

            while (rows.hasNextLine()) {
                Scanner elements = new Scanner(rows.nextLine()).useDelimiter(delimiter);
                int column = 0;
                while (elements.hasNext()) {
                    String element = elements.next();
                    if (filteredColumns.containsKey(column)) {
                        resultRow[filteredColumns.get(column)] = element;
                    }
                    column++;
                }
                resultRows.add(String.join(delimiter, resultRow));
                Arrays.fill(resultRow, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultRows;
    }

    public static void writeData(List<String> resultRows) {

        try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(out.toString()))) {
            for (String row : resultRows) {
                outStream.write(row.getBytes());
                outStream.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearArguments() {
        filters.clear();
        path = null;
        out = null;
        delimiter = null;
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

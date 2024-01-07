package ru.job4j.filessearcher;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

public class Recorder {
    private String logFileName;

    public Recorder(String logFileName) {
        this.logFileName = logFileName;
    }

    public void recordData(List<Path> data) {
        try (FileWriter fileWriter = new FileWriter(logFileName)) {

            data.forEach(s -> {
                try {
                    fileWriter.write(s + "\r\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public boolean checkLogFile() throws IOException {
        boolean result = false;
        if (Files.isRegularFile(Paths.get(logFileName)) && Files.size(Paths.get(logFileName)) > 0) {
            result = true;
        }
        return result;
    }
}
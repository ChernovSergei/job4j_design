package ru.job4j.io;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteTxtFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataResult.txt")) {
            for (int i = 0; i < 9; i++) {
                out.write(("1 * " + i + " = " + i).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

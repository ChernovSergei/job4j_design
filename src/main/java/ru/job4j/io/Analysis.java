package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Analysis {
    public void unavaliable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {

            Boolean[] recordToggle = new Boolean[1];
            recordToggle[0] = true;
            String[] ending = new String[2];
            ending[0] = ";\n";
            ending[1] = ";";

            in.lines()
                    .filter(L -> recordToggle[0] && (L.split(" ", 2)[0].equals("400") || L.split(" ", 2)[0].equals("500"))
                    || !recordToggle[0] && !L.split(" ", 2)[0].equals("400") && !L.split(" ", 2)[0].equals("500"))
                    .forEach(L -> {
                        out.print(L.split(" ", 2)[1] + ending[recordToggle[0] ? 1 : 0]);
                        recordToggle[0] = !recordToggle[0];
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavaliable("data/server1.log", "data/target1.csv");
        analysis.unavaliable("data/server2.log", "data/target2.csv");
    }
}

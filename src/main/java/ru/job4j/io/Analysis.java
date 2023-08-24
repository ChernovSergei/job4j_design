package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Analysis {
    public void unavaliable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {

            String line = "";
            boolean toggleRecord = true;

            while ((line = in.readLine()) != null) {
                boolean equal400 = line.split(" ", 2)[0].equals("400");
                boolean equal500 = line.split(" ", 2)[0].equals("500");

                if (toggleRecord && (equal400 || equal500)) {
                    out.print(line.split(" ", 2)[1] + ";");
                    toggleRecord = false;
                }
                if (!toggleRecord && !equal400 && !equal500) {
                    out.println(line.split(" ", 2)[1] + ";");
                    toggleRecord = true;
                }
            }
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

package ru.job4j.filessearcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class IDEConsole implements IOConsole {
    String intructions = "Enter the data" + System.lineSeparator();

    public IDEConsole() {
        printMessage(intructions);
    }

    @Override
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public String getMessage() {
        String answer = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            answer = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
        return answer;
    }

    @Override
    public HashMap<String, String> getUserInput(String[] args) {
        HashMap<String, String> userInput = new HashMap<>();
        if (args.length == 0) {
            String userAnswer = getMessage();
            if (userAnswer.equals("exit")) {
                return null;
            }
            args = userAnswer.split(" ");
        }
        ArgsName argsName = ArgsName.of(args);
        userInput.put("d", argsName.get("d"));
        userInput.put("n", argsName.get("n"));
        userInput.put("t", argsName.get("t"));
        userInput.put("o", argsName.get("o"));
        return userInput;
    }

}

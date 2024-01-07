package ru.job4j.filessearcher;

import java.io.Console;
import java.util.HashMap;

public class OSConsole implements IOConsole {
    String intructions = "Enter exit or next data separated by space to get Files search result:"
                + System.lineSeparator()
                + "-d=\"start directory\" "
                + "-n=\"file name, mask or regular expression\""
                + "-t=\"search type (name/mask/regex)\""
                + "-o=\"result file name"
                + System.lineSeparator();
    Console console;

    public OSConsole() {
        console = System.console();
        printMessage(intructions);
    }

    @Override
    public void printMessage(String msg) {
        console.printf(msg);
    }

    @Override
    public String getMessage() {
        return console.readLine();
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

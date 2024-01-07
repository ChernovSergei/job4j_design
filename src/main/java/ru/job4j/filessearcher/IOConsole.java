package ru.job4j.filessearcher;

import java.util.HashMap;

public interface IOConsole {

    void printMessage(String msg);

    String getMessage();

    HashMap<String, String> getUserInput(String[] args);
}

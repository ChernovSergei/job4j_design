package ru.job4j.filessearcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.nio.file.Path;

public class FilesSearcher {
    public static void main(String[] args) throws IOException {
        IOConsole console = new OSConsole();
        HashMap<String, String> userInput = console.getUserInput(args);
        if (userInput == null) {
            return;
        }
        FindFile foundFiles = new FindFile(userInput.get("d"), userInput.get("n"), userInput.get("t"));
        List<Path> list = foundFiles.findFiles();
        Recorder record = new Recorder(userInput.get("o"));
        record.recordData(list);
        if (!record.checkLogFile()) {
            console.printMessage("Something went wrong :-(");
        }
        console.printMessage("Success!!!");
    }
}

package ru.job4j.filessearcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class FindFile {
    private String startDirectory;
    private String condition;
    private String searchType;

    public FindFile(String startDirectory, String condition, String searchType) {
        this.condition = condition;
        this.startDirectory = startDirectory;
        this.searchType = searchType;
    }

    public List<Path> findFiles() throws IOException {
        if (searchType.equals("name")) {
            return Search.search(Paths.get(startDirectory), p -> p.toFile().getName().endsWith(condition));
        }
        if (searchType.equals("regex")) {
            Pattern pattern = Pattern.compile(condition);
            return Search.search(Paths.get(startDirectory), p -> pattern.matcher(p.toFile().getName()).matches());
        }
        if (searchType.equals("mask")) {
            if (condition.contains(".")) {
                condition = condition.replace(".", "\\.");
            }
            if (condition.contains("*")) {
                condition = condition.replace("*", "[a-zA-Z]+");
            }
            if (condition.contains("?")) {
                condition = condition.replace("?", "[a-zA-Z]?");
            }
            Pattern pattern = Pattern.compile(condition);
            return Search.search(Paths.get(startDirectory), p -> pattern.matcher(p.toFile().getName()).matches());

        }
        return null;
    }
}

package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {

        List<String> logs = new LinkedList<>();
        List<String> botPhrases = readPhrases();
        String question = "";
        int phraseID;
        Random random = new Random();
        boolean botSwitcher = true;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Бот приветствует тебя! Задавай вопрос!");
            while (!OUT.equals(question)) {
                question = reader.readLine();
                phraseID = Math.abs(random.nextInt() % botPhrases.size());
                logs.add(LocalDateTime.now() + ". User: " + question);

                if (STOP.equals(question) || OUT.equals(question)) {
                    botSwitcher = false;
                }
                if (CONTINUE.equals(question)) {
                    botSwitcher = true;
                }
                if (botSwitcher) {
                    System.out.println(botPhrases.get(phraseID));
                    logs.add(LocalDateTime.now().toString() + ". Bot: " + botPhrases.get(phraseID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> answers = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter saving = new PrintWriter(new FileWriter(path))) {
            log.forEach(saving::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:/projects/job4j_design/data/botLog.txt", "C:/projects/job4j_design/data/botAnswers.txt");
        cc.run();
    }
}
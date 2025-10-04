package ru.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        List<String> answers = readPhrases();
        List<String> dialogue = new ArrayList<>();
        boolean activeBot = true;
        boolean needAnswer = true;
        while (activeBot) {
            String userMsg = scanner.nextLine();
            String answer = randomString(answers);
            dialogue.add(userMsg);
            if (userMsg.equals(OUT)) {
                activeBot = false;
            }
            if (userMsg.equals(STOP)) {
                needAnswer = false;
            }
            if (userMsg.equals(CONTINUE)) {
                System.out.println(answer);
                dialogue.add(answer);
            }
            if (needAnswer) {
                System.out.println(answer);
                dialogue.add(answer);
            }
        }
        saveLog(dialogue);
    }

    List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader answersReader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            answersReader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String randomString(List<String> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("File doesn't contains answers");
        }
        return (list.get((int) (Math.random() * list.size())));
    }

    private void saveLog(List<String> log) {
        try (PrintWriter logger = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(logger::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/chat_log.txt", "./data/bot_answers.txt");
        consoleChat.run();

    }
}

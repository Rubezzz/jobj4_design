package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        String send = "";
        boolean flagStop = false;
        Random random = new Random();
        while (!OUT.equals(send)) {
            Scanner scanner = new Scanner(System.in);
            send = scanner.nextLine();
            log.add(send);
            if (STOP.equals(send)) {
                flagStop = true;
            }
            if (CONTINUE.equals(send)) {
                flagStop = false;
            }
            if (flagStop) {
                continue;
            }
            String answer = answers.get(random.nextInt(answers.size() - 1));
            System.out.println(answer);
            log.add(answer);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./bot/log.txt", "./bot/answers.txt");
        cc.run();
    }
}
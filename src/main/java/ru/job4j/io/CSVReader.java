package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        List<String> lines = readCSV(argsName.get("path"));
        LinkedHashMap<String, Integer> filteredLines = new LinkedHashMap<>();
        List.of(argsName.get("filter").split(",")).forEach(s -> filteredLines.put(s, -1));
        LinkedHashMap<Integer, String> lineAfterFilt = new LinkedHashMap<>();
        List<String> rslLines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            var scanner = new Scanner(new ByteArrayInputStream(lines.get(i).getBytes()))
                    .useDelimiter(argsName.get("delimiter"));
            StringJoiner rslLine = new StringJoiner(argsName.get("delimiter"));
            int j = 0;
            if (i != 0) {
                filteredLines.forEach((k, v) -> lineAfterFilt.put(v, null));
            }
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (i == 0) {
                    if (filteredLines.containsKey(next)) {
                        filteredLines.put(next, j);
                    }
                } else {
                    if (lineAfterFilt.containsKey(j)) {
                        lineAfterFilt.put(j, next);
                    }
                }
                j++;
            }
            if (i != 0) {
                lineAfterFilt.forEach((k, v) -> rslLine.add(v));
            } else {
                filteredLines.forEach((k, v) -> rslLine.add(k));
            }
            rslLines.add(rslLine.toString());
        }
        if ("stdout".equals(argsName.get("out"))) {
            rslLines.forEach(System.out::println);
        } else {
            saveInFile(rslLines, argsName.get("out"));
        }
    }

    private static List<String> readCSV(String path) {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private static void validateArgs(ArgsName args) {
        if (!Files.isRegularFile(Path.of(args.get("path")))) {
            throw new IllegalArgumentException("Error: invalid path argument!");
        }
        if (!"stdout".equals(args.get("out")) || args.get("out").matches("^(.+)/([^/]+)$")) {
            throw new IllegalArgumentException("Error: invalid out argument!");
        }
    }

    private static void saveInFile(List<String> lines, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            lines.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }
}
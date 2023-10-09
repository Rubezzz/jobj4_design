package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] arg) {
        if (arg.length != 2) {
            throw new IllegalArgumentException("Invalid number of startup arguments.");
        }
        if (arg[0].isEmpty()) {
            throw new IllegalArgumentException("Folder argument is empty.");
        }
        if (arg[1].isEmpty()) {
            throw new IllegalArgumentException("Filter argument is empty.");
        }
        if (!arg[1].matches("^\\.[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid filter argument. Example \".js\"");
        }
    }
}
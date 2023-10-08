package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> allPaths = new HashMap<>();
    private List<Path> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
            if (!allPaths.containsKey(fileProperty)) {
                ArrayList<Path> list = new ArrayList<>();
                list.add(file);
                allPaths.put(fileProperty, list);
            } else {
                allPaths.get(fileProperty).add(file);
            }
        }
        duplicates = allPaths.values().stream().filter(e -> e.size() >= 2).flatMap(List::stream).toList();
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        return duplicates;
    }
}

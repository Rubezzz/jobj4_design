package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath().substring(2)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath().substring(2)));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName args) {
        if (!Files.isDirectory(Path.of(args.get("d")))) {
            throw new IllegalArgumentException("Error: invalid directory argument!");
        }
        if (args.get("e").charAt(0) != '.') {
            throw new IllegalArgumentException("Error: invalid exclude argument!");
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Error: invalid output argument!");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        List<Path> sources = Search.search(Path.of(argsName.get("d")), path -> !path.toFile().getName().endsWith(argsName.get("e")));
        sources.forEach(System.out::println);
        Zip zip = new Zip();
        zip.packFiles(sources, new File(argsName.get("o")));

        Zip zip2 = new Zip();
        zip2.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
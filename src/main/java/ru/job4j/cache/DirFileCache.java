package ru.job4j.cache;

import java.io.*;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String path = String.format("%s%s%s", cachingDir, File.separator, key);
        String result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            result = reader.lines().collect(Collectors.joining());
            put(key, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
package ru.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cachingDir + "/" + key))) {
            StringBuilder builder = new StringBuilder();
            reader.lines()
                    .forEach(s -> builder.append(s).append("\n"));
            return builder.toString();
        } catch (IOException e) {
            System.out.println("File is missing");
        }
        return null;
    }
}
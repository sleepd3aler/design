package ru.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(string -> !string.isEmpty() && !string.startsWith("#"))
                    .forEach(String -> {
                        String[] parts = String.split("=", 2);
                        validate(parts);
                        this.values.put(parts[0], parts[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] parts) {
        if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException();
        }

    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}

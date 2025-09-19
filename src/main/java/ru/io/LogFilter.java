package ru.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader
                    .lines()
                    .filter(String -> {
                                String[] parts = String.split(" ");
                                return parts[parts.length - 2].equals("404");
                            }
                    )
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}

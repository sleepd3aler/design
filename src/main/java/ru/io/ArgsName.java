package ru.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            String[] parts = validateArgs(arg);
            values.put(parts[0], parts[1]);
        }
    }

    private String[] validateArgs(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(
                    "Error: This argument '" + arg + "' does not start with a '-' character");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(
                    "Error: This argument '" + arg + "' does not contain an equal sign");
        }
        String[] parts = arg.replace("-", "").split("=", 2);
        if (parts[0].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
        }
        if (parts[1].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
        }
        return parts;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8="});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("Xms"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

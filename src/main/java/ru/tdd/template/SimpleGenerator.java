package ru.tdd.template;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//I am ${name}, My age is ${age}! I have a ${animal} with name: ${nickname}, his favorite food is: ${food}."
//        String temp = "I am ${name}, My age is ${}! ";

public class SimpleGenerator implements Generator {
    private final Pattern pattern = Pattern.compile("\\$\\{([^${}]*?)}");

    @Override
    public String produce(String template, Map<String, String> args) {
        Matcher matcher = pattern.matcher(template);
        if (args.isEmpty() || !exist(matcher)) {
            throw new IllegalArgumentException();
        }
        validateMap(args);
        matcher.reset();
        return generateString(template, args, matcher);
    }

    private String generateString(String template, Map<String, String> args, Matcher matcher) {
        StringBuilder res = new StringBuilder();
        String temp = template;
        Set<String> keys = new HashSet<>();
        int sKeyCounter = 0;
        while (matcher.find()) {
            checkDuplicates(keys, matcher.group());
            String key = matcher.group(1);
            validateKey(key);
            String value = args.get(key);
            validateValue(value);
            res = new StringBuilder(temp.replace(matcher.group(), value));
            temp = res.toString();
            sKeyCounter++;
        }
        validateKeysCount(counter -> counter == args.size(), sKeyCounter);
        return res.toString();
    }

    private void validateMap(Map<String, String> args) {
        for (Map.Entry<String, String> entry : args.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                throw new IllegalArgumentException();
            }
            if (entry.getValue().matches(pattern.toString()) || entry.getKey().matches(pattern.toString())) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDuplicates(Set<String> keys, String key) {
        if (!keys.add(key)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateKeysCount(Predicate<Integer> condition, int counter) {
        if (!condition.test(counter)) {
            throw new IllegalArgumentException();
        }

    }

    private void validateKey(String key) {
        if (key.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
    }

    private boolean exist(Matcher matcher) {
        return matcher.find();
    }
}

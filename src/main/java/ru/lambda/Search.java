package ru.lambda;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Search {
    static List<File> findByMask(List<File> files, String mask) {
        List<File> result = new ArrayList<>();
        for (File file : files) {
            if (Pattern.matches(mask, file.getName())) {
                result.add(file);
            }
        }
        return result;
    }

    static List<File> findByName(List<File> files, String name) {
        List<File> result = new ArrayList<>();
        for (File file : files) {
            if (name.equals(file.getName())) {
                result.add(file);
            }
        }
        return result;
    }

    static List<File> findByExtension(List<File> files, String extension) {
        List<File> result = new ArrayList<>();
        for (File file : files) {
            if (file.getName().endsWith(extension)) {
                result.add(file);
            }
        }
        return result;
    }

}

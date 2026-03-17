package ru.cache.menu;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    protected boolean validateDir(String path) {
        if (path.isBlank() || !Files.isDirectory(Path.of(path))) {
            System.out.println("Директория отсутствует или указана неверно.");
            return false;
        }
        return true;
    }

    protected boolean validateFileName(String fileName) {
        if (!fileName.endsWith(".txt") || fileName.isBlank()) {
            System.out.println("Некорректное расширение или имя файла.");
            return false;
        }
        return true;
    }

    protected boolean validateChoice(String choice) {
        if (!choice.matches("[1-3]")) {
            System.out.println("Выберите один из пунктов меню.");
        return false;
        }
        return true;
    }
}

package ru.cache.menu;

import java.io.IOException;
import java.util.Scanner;
import ru.cache.AbstractCache;
import ru.cache.DirFileCache;

public class Emulator {
    static final String TO_CACHE = "1";
    static final String FROM_CACHE = "2";
    static final String EXIT = "3";
    static final String ASK_FILE_NAME = "Введите имя файла:";

    public static void main(String[] args) throws IOException {
        System.out.println("Введите кешируемую директорию: ");
        Scanner scanner = new Scanner(System.in);
        String userDir = scanner.nextLine();
        AbstractCache<String, String> cache = new DirFileCache(userDir);
        boolean run = true;
        while (run) {
            System.out.println("""
                    1. Загрузить содержимое файла в кэш
                    2. Получить содержимое файла из кэша
                    3. Завершить программу
                    """);

            String choice = scanner.nextLine();
            String fileName;
            switch (choice) {
                case TO_CACHE -> {
                    System.out.println(ASK_FILE_NAME);
                    fileName = scanner.nextLine();
                    cache.put(fileName, cache.get(fileName));
                }
                case FROM_CACHE -> {
                    System.out.println(ASK_FILE_NAME);
                    fileName = scanner.nextLine();
                    String result = cache.get(fileName);
                    if (result != null) {
                        System.out.println(result);
                    }
                }
                case EXIT -> {
                    System.out.println("Программа завершена.");
                    run = false;
                }

            }

        }
    }
}

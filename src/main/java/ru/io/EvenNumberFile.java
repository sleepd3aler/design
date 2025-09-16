package ru.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static String readFile(String path) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream(path)) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void printEvenNumbers(String numbers) {
        String[] lines = numbers.split(System.lineSeparator());
        for (String line : lines) {
            if (Integer.parseInt(line) % 2 == 0) {
                System.out.println("The number: " + line + " is even");
            }
        }
    }

    public static void main(String[] args) {
        printEvenNumbers(readFile("data/even.txt"));
    }
}

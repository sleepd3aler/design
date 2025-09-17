package ru.io;

import java.io.FileInputStream;
import java.util.Scanner;

public class EvenNumberFile {
    public static String readFile(String path) {
        Scanner scanner = new Scanner(path);
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream(scanner.nextLine())) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    private static int isNumber(String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }

    }

    private static boolean isEven(String line) {
        return isNumber(line) % 2 == 0;
    }

    public static void printEvenNumbers(String numbers) {
        String[] lines = numbers.split(System.lineSeparator());
        for (String line : lines) {
            if (isEven(line)) {
                System.out.println("The Number: " + line + " is even");
            }
        }
    }

    public static void main(String[] args) {
        printEvenNumbers(readFile("data/even.txt"));
    }
}

package ru.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static String readFile(FileInputStream inputStream) throws IOException {
        StringBuilder text = new StringBuilder();
        int read;
        while ((read = inputStream.read()) != -1) {
            text.append((char) read);
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
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
            printEvenNumbers(readFile(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

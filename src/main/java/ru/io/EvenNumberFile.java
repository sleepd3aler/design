package ru.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void evenOutputBuilder(FileInputStream inputStream) throws IOException {
        StringBuilder text = new StringBuilder();
        int read;
        while ((read = inputStream.read()) != -1) {
            text.append((char) read);
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            if (Integer.parseInt(line) % 2 == 0) {
                System.out.println("The number: " + line + " is even");
            }
        }
    }

    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            evenOutputBuilder(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

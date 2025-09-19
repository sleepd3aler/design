package ru.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static StringBuilder multiplyTable(int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                int number = i * j;
                result.append(String.format("%4d", number));
            }
            result.append(System.lineSeparator());
        }
        return result;
    }

    public static void main(String[] args) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/result.txt")))) {
            output.println("Hello, World!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

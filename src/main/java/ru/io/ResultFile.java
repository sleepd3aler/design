package ru.io;

import java.io.FileOutputStream;

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
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            output.write(multiplyTable(10).toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

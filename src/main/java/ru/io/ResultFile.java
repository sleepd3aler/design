package ru.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static int[][] multiply() {
        int[][] result = new int[10][10];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = (i + 1) * (j + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] multiplicationTable = multiply();
        StringBuilder outputBuilder = new StringBuilder();
        for (int[] matrix : multiplicationTable) {
            for (int j = 0; j < multiplicationTable.length; j++) {
                outputBuilder.append(String.format("%4d", matrix[j]));
            }
            outputBuilder.append(System.lineSeparator());
        }
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            output.write(outputBuilder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/h82w8t/Desktop/Projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw  new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }

        for (File subfile : file.listFiles()) {
            System.out.printf("File : %s, has size : %s%n", subfile.getName(), subfile.length());
        }
    }
}

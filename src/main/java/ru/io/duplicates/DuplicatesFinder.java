package ru.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("/Users/h82w8t/Desktop/Projects/design/src/main/java/ru/io/files"),
                duplicatesVisitor);

    }
}

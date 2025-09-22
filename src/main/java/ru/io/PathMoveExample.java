package ru.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathMoveExample {
    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("path/paths");
        Files.createDirectories(directory);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);
        Files.delete(directory);
    }
}

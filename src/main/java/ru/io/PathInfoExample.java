package ru.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInfoExample {
    public static void main(String[] args)  throws IOException {
        Path directory = Path.of("path_move/paths");
        Files.createDirectories(directory);
        Path target = Paths.get("path_move");
        Path pathOne = Path.of("path_move/paths/path1.txt");
        Files.createFile(pathOne);
        Path pathTwo = Path.of("path_move/path2.txt");
        Files.createFile(pathTwo);
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);
    }
}

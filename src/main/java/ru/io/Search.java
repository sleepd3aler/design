package ru.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {

    private static Path root;

    private static String extension = "";

    private class SearchFiles implements FileVisitor<Path> {

        private final Predicate<Path> condition;

        private final List<Path> paths = new ArrayList<>();

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        public List<Path> getPaths() {
            return paths;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (condition.test(file)) {
                this.paths.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
       if (args.length < 2) {
           throw new IllegalArgumentException("Not enough arguments for execute. Enter a PATH and FILE_EXTENSION");
       }
       if (args[0] == null) {
           throw new IllegalArgumentException("ROOT is not available");
       }

       if (args[1] == null) {
           throw new IllegalArgumentException("FILE_EXTENSION is not available");
       }
            Search.root = Path.of(args[0]);
            Search.extension = args[1];
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Search search = new Search();
        search.search(root, file -> file.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }
}

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

import static ru.io.Search.SearchFiles.extension;
import static ru.io.Search.SearchFiles.root;

public class Search {

    class SearchFiles implements FileVisitor<Path> {
        static Path root;

        static String extension = "";

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
        if (searcher.getPaths().isEmpty()) {
            throw new IllegalArgumentException("No such files with current extension");
        }
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments for execute. Enter a PATH and FILE_EXTENSION");
        }
        root = Path.of(args[0]);
        extension = args[1];
        if (!Files.exists(root)) {
            throw new IllegalArgumentException("No such directory");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Search search = new Search();
        search.search(root, file -> file.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }
}

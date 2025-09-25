package ru.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private Path root;
    private String extension;

    public Search(Path root, String extension) {
        this.root = root;
        this.extension = extension;
    }

    public Path getRoot() {
        return root;
    }

    public String getExtension() {
        return extension;
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
        if (!Files.exists(Path.of(args[0])) && !Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("No such directory");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Illegal extension format");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Search search = new Search(Path.of(args[0]), args[1]);
        search.search(search.getRoot(), file -> file.toFile().getName()
                .endsWith(search.getExtension())).forEach(System.out::println);
    }
}

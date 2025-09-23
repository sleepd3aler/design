package ru.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> duplicateFiles = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty current = new FileProperty(file.toFile().length(), file.getFileName().toString());
        List<Path> duplicatePaths = new ArrayList<>();
        if (file.getFileName().toString().equals(current.getName()) && file.toFile().length() == current.getSize()) {
            duplicatePaths.add(file);
        }
        duplicateFiles.put(current, duplicatePaths);
        return super.visitFile(file, attrs);
    }

    public void printInfo() {
        duplicateFiles.forEach((key, value) -> {
            System.out.format("""
                    File name: %s;
                    Has Size: %s;
                    Founded: %d duplicates at paths:
                    """, key.getName(), key.getSize(), value.size());
            value.forEach(System.out::println);
        });
    }

}

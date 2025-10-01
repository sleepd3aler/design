package ru.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * -d=c:\project\ -e=.class -o=project.zip
 * <p>
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы с расширением class.
 * -o - output - во что мы архивируем.
 */
public class Zip {
    public void packFiles(List<Path> sources, File target) throws IOException {
        try (ZipOutputStream toZip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                toZip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()));
                toZip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Not enough arguments for execute. Enter a PATH, FILE_EXTENSION, and PACKAGE_TYPE"
            );
        }
        Path root = Path.of(args[0].substring(3));
        if (!Files.exists(root) && !Files.isDirectory(root)) {
            throw new IllegalArgumentException("No such directory");
        }
        String extensionToSkip = args[1].substring(3);
        if (!extensionToSkip.startsWith(".")) {
            throw new IllegalArgumentException("Illegal extension format");
        }

        String packageExtension = args[2].substring(3);
        if (!packageExtension.endsWith(".zip")) {
            throw new IllegalArgumentException("Illegal package format");
        }

    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Zip zip = new Zip();
        zip.packSingleFile(new File("./pom.xml"),
                new File("./pom.zip"));
        ArgsName argsName = ArgsName.of(args);
        Search search = new Search(Path.of(argsName.get("d")), argsName.get("e"));
        SearchFiles searcher = new SearchFiles(file -> file.toFile()
                .getName().endsWith(search.getExtension()));
        List<Path> result = search.search(search.getRoot(), searcher);
        zip.packFiles(result, new File(argsName.get("o")));
    }
}

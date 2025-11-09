package filesearcher;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class FileSearcher {

    private final ArgsParser parser;

    public FileSearcher(ArgsParser parser) {
        this.parser = parser;
    }

    public void executeSearch() throws IOException {
        Path root = Path.of(parser.getDir());
        Path output = Path.of(parser.getOutputPath());
        String searchType = parser.getTypeOfSearch();
        Search searcher = new Search(getPredicate(searchType));
        List<Path> foundedFiles = search(root, searcher);
        try (
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(output.toFile()), StandardCharsets.UTF_8))
        ) {
            for (Path path : foundedFiles) {
                writer.write(path.toFile().getAbsolutePath());
                writer.newLine();
            }
        }
    }

    private List<Path> search(Path root, Search searcher) throws IOException {
        Files.walkFileTree(root, searcher);
        if (searcher.getPaths().isEmpty()) {
            throw new IllegalArgumentException("No such file with current name");
        }
        return searcher.getPaths();
    }

    private Predicate<Path> getPredicate(String type) {
        Predicate<Path> result;
        String name = parser.getName();
        switch (type) {
            case "name":
                result = (path -> path.toFile().getName().equals(name));
                break;
            case "mask":
                String regex = name.replace(".", "\\.")
                        .replace("?", ".")
                        .replace("*", ".*");
                result = (path -> path.toFile().getName().matches(regex));
                break;
            case "regex":
                result = (path -> path.toFile().getName().matches(name));
                break;
            default:
                throw new IllegalArgumentException("Incorrect type of search provided, must be : name, mask or regex");
        }
        return result;
    }
}

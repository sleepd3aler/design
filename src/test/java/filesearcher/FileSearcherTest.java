package filesearcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileSearcherTest {
    //-d=c:  -n=*.?xt -t=mask -o=log.txt
    @Test
    void whenSearchByNameIsSuccessful(@TempDir Path tempDir) throws IOException {
        Path tempOutput = tempDir.resolve("log.txt");
        Path testFile = tempDir.resolve("first.txt");
        Files.createFile(testFile);
        String[] args = {"-d=" + tempDir, "-n=first.txt", "-t=name", "-o=" + tempOutput};
        ArgsParser parser = new ArgsParser(args);
        FileSearcher searcher = new FileSearcher(parser);
        searcher.executeSearch();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutput.toFile()))) {
            StringBuilder result = new StringBuilder();
            reader.lines().forEach(result::append);
            assertThat(result).contains(testFile.getFileName().toString());
        }
    }

    @Test
    void whenSearchByMaskIsSuccessful(@TempDir Path tempDir) throws IOException {
        Path tempOutput = tempDir.resolve("log.txt");
        Path testFile = tempDir.resolve("first.txt");
        Path testFile2 = tempDir.resolve("second.txt");
        Files.createFile(testFile);
        Files.createFile(testFile2);
        String[] args = {"-d=" + tempDir, "-n=*.?xt", "-t=mask", "-o=" + tempOutput};
        ArgsParser parser = new ArgsParser(args);
        FileSearcher searcher = new FileSearcher(parser);
        searcher.executeSearch();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutput.toFile()))) {
            StringBuilder result = new StringBuilder();
            reader.lines().forEach(result::append);
            assertThat(result).contains(testFile.getFileName().toString(), testFile2.getFileName().toString());
        }
    }

    @Test
    void whenSearchByRegexIsSuccessful(@TempDir Path tempDir) throws IOException {
        Path tempOutput = tempDir.resolve("log.txt");
        Path testFile = tempDir.resolve("first.txt");
        Path testFile2 = tempDir.resolve("sec.txt");
        Files.createFile(testFile);
        Files.createFile(testFile2);
        String[] args = {"-d=" + tempDir, "-n=.*\\.*", "-t=regex", "-o=" + tempOutput};
        ArgsParser parser = new ArgsParser(args);
        FileSearcher searcher = new FileSearcher(parser);
        searcher.executeSearch();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutput.toFile()))) {
            StringBuilder result = new StringBuilder();
            reader.lines().forEach(result::append);
            assertThat(result).contains(testFile.getFileName().toString(), testFile2.getFileName().toString());
        }
    }

    @Test
    void whenMissingFileNameProvidedThenExceptionThrown(@TempDir Path tempDir) throws IOException {
        Path tempOutput = tempDir.resolve("log.txt");
        Path testFile = tempDir.resolve("first.txt");
        Path testFile2 = tempDir.resolve("sec.txt");
        Files.createFile(testFile);
        Files.createFile(testFile2);
        String[] args = {"-d=" + tempDir, "-n=third.txt", "-t=name", "-o=" + tempOutput};
        ArgsParser parser = new ArgsParser(args);
        FileSearcher searcher = new FileSearcher(parser);
        assertThatThrownBy(searcher::executeSearch)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such file with current name");
    }

}
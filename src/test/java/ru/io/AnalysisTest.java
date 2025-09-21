package ru.io;

import java.io.*;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {

    private Analysis serverAnalysis;
    String ln;

    @BeforeEach
    void init() {
        this.serverAnalysis = new Analysis();
        this.ln = System.lineSeparator();
    }

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.print(
                    """
                            200 10:56:01
                            300 10:57:01
                            400 10:58:01
                            300 10:59:01
                            500 11:01:02
                            200 11:02:02
                            """
            );
        }
        File target = tempDir.resolve("target.txt").toFile();
        serverAnalysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines()
                    .forEach(line -> result.append(line).append(ln));
        }
        assertThat(
                """
                        10:58:01;10:59:01;
                        11:01:02;11:02:02;
                        """
        ).hasToString(result.toString());
    }

    @Test
    void whenAlwaysUnavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.print("""
                    200 10:01:01
                    400 10:02:01
                    """);
        }
        File target = tempDir.resolve("target.txt").toFile();
        serverAnalysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines()
                    .forEach(result::append);
        }
        assertThat("Server failed and didn't recovered from: 10:02:01;").hasToString(result.toString());
    }

}
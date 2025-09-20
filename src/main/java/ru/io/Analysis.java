package ru.io;

import java.io.*;

public class Analysis {
    private String start = "";
    private String end = "";

    public void unavailable(String source, String target) {
        try (
                BufferedReader input = new BufferedReader(new FileReader(source));
                PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))
        ) {
            input.lines()
                    .forEach(line -> {
                        if (line.startsWith("400") || line.startsWith("500")) {
                            this.start = line.trim().substring(4);
                            end = "";
                        } else {
                            this.end = line.trim().substring(4);
                        }
                        if (!this.start.isEmpty() && !this.end.isEmpty()) {
                            output.format("%s;%s;%n", start, end);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

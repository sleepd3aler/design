package ru.report_generation_app.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileConfig implements Config {
    private static final Properties properties = new Properties();

    public void load(String file) {
        try (InputStream input = FileConfig.class.getClassLoader().getResourceAsStream(file)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String key) {
        return properties.getProperty(key);
    }

    @Override
    public void close() throws Exception {

    }
}

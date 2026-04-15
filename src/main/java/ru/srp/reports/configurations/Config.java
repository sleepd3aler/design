package ru.srp.reports.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    public void load(String file) {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(file)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void setFormat(String key, String value) {
        properties.setProperty(key, value);
    }
}

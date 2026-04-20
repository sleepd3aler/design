package ru.report_generation_app.configurations;

public interface Config extends AutoCloseable {
    String get(String string);
}
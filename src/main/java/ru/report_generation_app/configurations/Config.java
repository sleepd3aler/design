package ru.report_generation_app.configurations;

public interface Config extends AutoCloseable {
    int getInterval(String string);

    String get(String string);
}
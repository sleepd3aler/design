package ru.report_generation_app.formatter;

public interface DateTimeParser<T> {
    String parse(T t, String dateFormat);
}

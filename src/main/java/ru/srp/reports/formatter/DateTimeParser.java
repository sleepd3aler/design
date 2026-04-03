package ru.srp.reports.formatter;

public interface DateTimeParser<T> {
    String parse(T t);
}

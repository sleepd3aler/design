package ru.set;

public interface SimpleSet<T> extends Iterable<T> {
    boolean add(T value);

    boolean contains(T value);
}

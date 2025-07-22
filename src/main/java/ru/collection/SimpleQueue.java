package ru.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    public T poll() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        if (output.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
    }

}

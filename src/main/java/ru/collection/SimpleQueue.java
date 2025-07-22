package ru.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int size;

    public T poll() {
        checkSize();
        try {
            T deleted = output.pop();
            size--;
            return deleted;
        } catch (NoSuchElementException e) {
            for (int i = 0; i < size; i++) {
                output.push(input.pop());
            }
        }
        size--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        size++;
    }

    private void checkSize() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

}

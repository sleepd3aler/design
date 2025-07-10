package ru.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        if (capacity == 0) {
            capacity = 1;
        }
        container = (T[]) new Object[capacity];
    }

    private void grow() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public void add(T value) {
        container[size++] = value;
        modCount++;
        grow();
    }

    @Override
    public T set(int index, T newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removed = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > currentIndex;
            }

            @Override
            public T next() {
                if (!hasNext() || currentIndex < size && container[currentIndex] == null) {
                    throw new NoSuchElementException();
                }
                return container[currentIndex++];
            }
        };
    }

}

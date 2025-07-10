package ru.collection;
//
import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            increaseLength();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
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
        Objects.checkIndex(index, size);
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

    private void increaseLength() {
        container = Arrays.copyOf(container, container.length + 1 * 2);
    }

}

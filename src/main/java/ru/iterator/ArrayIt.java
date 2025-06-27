package ru.iterator;

import java.util.Iterator;

public class ArrayIt implements Iterator {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Object next() {
        return data[point++];
    }
}

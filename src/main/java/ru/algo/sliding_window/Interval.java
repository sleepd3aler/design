package ru.algo.sliding_window;

public class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s", start, end);
    }
}

package ru.srp;

import java.util.Formatter;
import java.util.List;

public class SimpleSequenceFormatter implements SequenceFormatter<Integer> {
    private final Formatter formatter;

    public SimpleSequenceFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String format(List<Integer> sequence) {
        for (int i = 0; i < sequence.size(); i++) {
            formatter.format("%d", sequence.get(i));
            if (i < sequence.size() - 1) {
                formatter.format(" -> ");
            }
        }
        formatter.format(".");
        return formatter.toString();
    }
}

package ru.ood.srp;

import java.util.List;

public interface SequenceFormatter<T> {
    String format(List<T> sequence);
}

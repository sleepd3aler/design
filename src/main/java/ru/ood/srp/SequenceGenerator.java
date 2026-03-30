package ru.ood.srp;

import java.util.List;

public interface SequenceGenerator<T> {
    List<T> generate(int size);
}

package ru.srp;

import java.util.List;

public class ValidateGenerator<T> implements SequenceGenerator<T> {
    private final SequenceGenerator<T> generator;

    public ValidateGenerator(SequenceGenerator<T> generator) {
        this.generator = generator;
    }

    @Override
    public List<T> generate(int size) {
        validateSize(size);
        return generator.generate(size);
    }

    private void validateSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
    }
}

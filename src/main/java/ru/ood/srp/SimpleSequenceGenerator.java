package ru.ood.srp;

import java.util.List;
import java.util.stream.IntStream;

public class SimpleSequenceGenerator implements SequenceGenerator<Integer> {
    private final NumberGenerator<Integer> numberGenerator;

    public SimpleSequenceGenerator(NumberGenerator<Integer> generator) {
        this.numberGenerator = generator;
    }

    @Override
    public List<Integer> generate(int size) {
        return IntStream.range(0, size)
                .map(i -> numberGenerator.generate())
                .boxed()
                .toList();
    }
}

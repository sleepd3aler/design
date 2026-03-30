package ru.ood.srp;

import java.util.Random;

public class SimpleNumberGenerator implements NumberGenerator<Integer> {
    private final Random random;
    private final int diapason;

    public SimpleNumberGenerator(Random random, int diapason) {
        this.random = random;
        this.diapason = diapason;
    }

    @Override
    public Integer generate() {
        return random.nextInt(diapason);
    }
}

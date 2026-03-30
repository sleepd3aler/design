package ru.ood.srp;

import java.util.Random;

public class SimpleNumberGenerator implements NumberGenerator<Integer> {
    Random random = new Random();

    public SimpleNumberGenerator() {
    }

    @Override
    public Integer generate() {
        return random.nextInt(10);
    }
}

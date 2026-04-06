package ru.ood.ocp.payment_service.types;

public class Crypto implements Type {
    private final String name;

    public Crypto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void process(double amount) {
        System.out.printf("Processing %s payment: %.2f%n", name, amount);
    }
}

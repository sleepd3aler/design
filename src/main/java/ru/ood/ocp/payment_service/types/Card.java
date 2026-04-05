package ru.ood.ocp.payment_service.types;

public class Card implements Type {
    private String name;

    public Card(String name) {
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

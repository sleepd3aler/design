package ru.ood.ocp.payment_service.types;

public class Paypal implements Type {
    private String name;

    public Paypal(String name) {
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

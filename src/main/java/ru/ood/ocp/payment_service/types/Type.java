package ru.ood.ocp.payment_service.types;

public interface Type {
    String getName();

    void process(double amount);
}

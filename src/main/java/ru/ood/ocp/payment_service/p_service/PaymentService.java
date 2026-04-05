package ru.ood.ocp.payment_service.p_service;

import ru.ood.ocp.payment_service.types.Type;

public interface PaymentService {
    void process(String type, double amount);

    void addNewPayment(Type type);
}

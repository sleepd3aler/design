package ru.ood.ocp.payment_service.p_service;

import java.util.Map;
import java.util.function.Predicate;
import ru.ood.ocp.payment_service.types.Type;

public class SimplePayment implements PaymentService {
    private Map<String, Type> pTypes;

    public SimplePayment(Map<String, Type> pTypes) {
        this.pTypes = pTypes;
    }

    @Override
    public void process(String type, double amount) {
        checkExists(p -> pTypes.containsKey(type), type);
        Type paymentT = pTypes.get(type);
        paymentT.process(amount);
    }

    @Override
    public void addNewPayment(Type type) {
        if (type != null && !pTypes.containsKey(type.getName())) {
            pTypes.put(type.getName(), type);
        }
    }

    private void checkExists(Predicate<String> condition, String payment) {
        if (!condition.test(payment)) {
            throw new IllegalArgumentException("Unsupported payment type");
        }
    }
}

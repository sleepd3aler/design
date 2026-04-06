package ru.ood.ocp.payment_service.p_service;

import java.util.Map;
import java.util.function.Predicate;
import ru.ood.ocp.payment_service.types.Type;

public class SimplePayment implements PaymentService {
    private final Map<String, Type> paymentTypes;

    public SimplePayment(Map<String, Type> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    @Override
    public void process(String type, double amount) {
        checkExists(p -> paymentTypes.containsKey(type), type);
        Type paymentType = paymentTypes.get(type);
        paymentType.process(amount);
    }

    @Override
    public void addNewPayment(Type type) {
        if (type != null && !paymentTypes.containsKey(type.getName())) {
            paymentTypes.put(type.getName(), type);
        }
    }

    private void checkExists(Predicate<String> condition, String payment) {
        if (!condition.test(payment)) {
            throw new IllegalArgumentException("Unsupported payment type");
        }
    }
}

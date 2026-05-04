package ru.dip.order_service.storage;

import ru.dip.order_service.model.Order;

public class DryRun implements Repository {
    private int ids = 1;

    @Override
    public int saveOrder(Order order) {
        System.out.printf("Order #%d: %s, %.2f%n", ids, order.customer(), order.amount());
        return ids++;
    }
}

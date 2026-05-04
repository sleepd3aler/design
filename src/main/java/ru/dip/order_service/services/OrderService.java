package ru.dip.order_service.services;

import ru.dip.order_service.notification.Notification;
import ru.dip.order_service.storage.Repository;

public class OrderService {
    private Repository repository;
    private Notification notification;

    public OrderService(Repository repository, Notification notification) {
        this.repository = repository;
        this.notification = notification;
    }

    void placeOrder(String customer, double amount) {
        notification.send(repository.saveOrder(customer, amount));
    }
}

package ru.dip.order_service.services;

import ru.dip.order_service.notifications.Notification;
import ru.dip.order_service.storage.Repository;

public class OrderService {
    private Repository repository;
    private Notification notification;

    public OrderService(Repository repository, Notification notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void placeOrder(String customer, double amount) {
        int orderId = repository.saveOrder(customer, amount);
        notification.send(orderId);
    }
}

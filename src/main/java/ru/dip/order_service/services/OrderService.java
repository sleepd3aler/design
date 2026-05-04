package ru.dip.order_service.services;

import ru.dip.order_service.model.Order;
import ru.dip.order_service.notifications.Notification;
import ru.dip.order_service.storage.Repository;

public class OrderService {
    private Repository repository;
    private Notification notification;

    public OrderService(Repository repository, Notification notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void placeOrder(Order order) {
        int orderId = repository.saveOrder(order);
        notification.send(orderId);
    }
}

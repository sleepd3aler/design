package ru.dip.order_service.notification;

public class EmailNotificationService implements Notification {
    @Override
    public void send(int id) {
        System.out.printf("Order #%d created %n ", id);
    }
}

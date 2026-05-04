package ru.dip.order_service.notifications;

public class ConsoleNotificationSender implements Notification {
    @Override
    public void send(int id) {
        System.out.printf("Order #%d created%n", id);
    }
}

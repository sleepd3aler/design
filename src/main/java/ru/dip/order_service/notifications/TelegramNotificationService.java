package ru.dip.order_service.notifications;

public class TelegramNotificationService implements Notification {
    @Override
    public void send(int id) {
        System.out.printf("Notification: Order #%d created%n", id);
    }
}

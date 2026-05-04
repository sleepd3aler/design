package ru.dip.order_service.notifications;

public class EmailNotificationService {
    public void sendEmail(int id) {
        System.out.printf("Order #%d created %n ", id);
    }
}

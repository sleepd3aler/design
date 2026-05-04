package ru.dip.order_service.services;

import ru.dip.order_service.notifications.Notification;
import ru.dip.order_service.storage.Repository;

public class ServiceFactory {

    public static OrderService createService(Notification notification, Repository repository) {
        return new OrderService(repository, notification);
    }

}

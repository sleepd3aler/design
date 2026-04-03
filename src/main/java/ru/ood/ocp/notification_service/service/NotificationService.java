package ru.ood.ocp.notification_service.service;

import java.util.Map;
import java.util.function.Predicate;
import ru.ood.ocp.notification_service.types.Type;

public class NotificationService {

    private final Map<String, Type> notTypes;

    public NotificationService(Map<String, Type> notTypes) {
        this.notTypes = notTypes;
    }

    public void sendMessage(String type, String message) {
        checkExists(notification -> notTypes.containsKey(type), type);
        Type notification = notTypes.get(type);
        notification.send(message);
    }

    public void addNotification(Type type) {
        if (type != null && notTypes.containsKey(type.getName())) {
            notTypes.put(type.getName(), type);
        }
    }

    private <T> void checkExists(Predicate<T> condition, T type) {
        if (!condition.test(type)) {
            throw new IllegalArgumentException("Unknown type");
        }
    }
}

package ru.ood.ocp.notification_service.types;

public interface Type {
    String getName();

    void send(String message);
}

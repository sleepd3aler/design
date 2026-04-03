package ru.ood.ocp.notification_service.types;

public class Sms implements Type {
    private final String name;

    public Sms(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void send(String message) {
        System.out.println(getName() + ": " + message);
    }
}

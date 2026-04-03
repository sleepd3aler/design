package ru.ood.ocp.notification_service.types;

public class Push implements Type {
    private final String name;

    public Push(String name) {
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

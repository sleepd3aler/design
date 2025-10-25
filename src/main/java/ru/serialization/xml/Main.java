package ru.serialization.xml;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(
                false, 30, new Contact("11-111"), new String[]{"Worker", "Married"}
        );

        final Device device = new Device(
                false, 18000, "MacBook Air 2020(Intel i3)", new Seller("89783358791"),
                new String[]{"89787123313", "89788133712"}
        );
    }
}

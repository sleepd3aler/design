package ru.serialization.xml;

public class Seller {
    private final String phone;

    public Seller(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "phone='" + phone + '\'' +
                '}';
    }
}

package ru.serialization.xml;

import java.util.Arrays;

public class Device {
    private final boolean inOrder;
    private final int price;
    private final String name;
    private final Seller seller;
    private final String[] potentialBuyers;

    public Device(boolean inOrder, int price, String name, Seller seller, String[] buyers) {
        this.inOrder = inOrder;
        this.price = price;
        this.name = name;
        this.seller = seller;
        this.potentialBuyers = buyers;
    }

    @Override
    public String toString() {
        return "Device{" +
                "inOrder=" + inOrder +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", seller=" + seller +
                ", potentialBuyers=" + Arrays.toString(potentialBuyers) +
                '}';
    }
}

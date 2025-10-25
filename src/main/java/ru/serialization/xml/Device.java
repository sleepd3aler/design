package ru.serialization.xml;

import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device {
    @XmlAttribute
    private boolean inOrder;
    @XmlAttribute
    private int price;
    @XmlAttribute
    private String name;
    private Seller seller;

    @XmlElementWrapper(name = "potentialbuyers")
    @XmlElement(name = "potentialbuyer")
    private String[] potentialBuyers;

    public Device() {
    }

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

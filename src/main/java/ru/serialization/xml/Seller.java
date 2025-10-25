package ru.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
public class Seller {
    @XmlAttribute
    private  String phone;

    public Seller() {
    }

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

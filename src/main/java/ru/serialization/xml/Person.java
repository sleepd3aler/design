package ru.serialization.xml;

import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    public Person() {
    }

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;
    private Contact contact;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Person(boolean sex, int age, Contact contact, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", age=" + age +
                ", contact=" + contact +
                ", statuses=" + Arrays.toString(statuses) +
                '}';
    }
}

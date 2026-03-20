package ru.clone;

public class CloneTask {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("Warsaw");
        Person p1 = new Person("John", address);

        Person p2 = p1.clone();

        p2.name = "Mike";
        p2.address.city = "Berlin";

        System.out.println(p1.name); // John
        System.out.println(p1.address.city); // Berlin

        System.out.println(p2.name); // Mike
        System.out.println(p2.address.city); // Berlin

    }
}

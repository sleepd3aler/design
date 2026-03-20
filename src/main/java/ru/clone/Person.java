package ru.clone;

public class Person implements  Cloneable {
   public String name;
    public Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {
        Person personClone = (Person) super.clone();
         personClone.address = address.clone();
        return personClone;
    }
}

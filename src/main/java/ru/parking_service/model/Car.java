package ru.parking_service.model;

public class Car extends Vehicle {
    public Car(String model, String numbers, String color, int size) {
        super(model, numbers, color, size);
    }

    protected void validateVehicle(int size) {
        if (size != 1) {
            throw new IllegalArgumentException("Car size must be 1.");
        }
    }

}

package ru.parking_service.model;

import static ru.parking_service.constants.Constants.CAR_SIZE;

public class Car extends Vehicle {
    public Car(String model, String numbers, String color, int size) {
        super(model, numbers, color, size, Type.CAR);
        validateVehicle(size);
    }

    protected void validateVehicle(int size) {
        if (size != CAR_SIZE) {
            throw new IllegalArgumentException("Car size must be 1.");
        }
    }
}

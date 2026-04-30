package ru.parking_service.model;

import static ru.parking_service.constants.Constants.CAR_SIZE;

public class Truck extends Vehicle {

    public Truck(String model, String numbers, String color, int size) {
        super(model, numbers, color, size, Type.TRUCK);
        validateVehicle(size);
    }

    @Override
    protected void validateVehicle(int size) {
        if (size <= CAR_SIZE) {
            throw new IllegalArgumentException("Truck size must be more than 1.");
        }
    }
}

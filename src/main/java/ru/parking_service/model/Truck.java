package ru.parking_service.model;

public class Truck extends Vehicle {

    public Truck(String model, String numbers, String color, int size) {
        super(model, numbers, color, size, Type.TRUCK);
        validateVehicle(size);
    }

    @Override
    protected void validateVehicle(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Truck size must be more than 1.");
        }
    }
}

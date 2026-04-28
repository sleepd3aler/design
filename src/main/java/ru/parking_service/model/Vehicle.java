package ru.parking_service.model;

import java.awt.*;
import java.util.Objects;

public abstract class Vehicle {
    private String model;
    private String numbers;
    private String color;
    private int parkingPlace;
    private int size;
    private Type type;

    public Vehicle(String model, String numbers, String color, int size, Type type) {
        this.model = model;
        this.numbers = numbers;
        this.color = color;
        this.size = size;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(int parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", numbers='" + numbers + '\'' +
                ", color='" + color + '\'' +
                ", parkingPlace=" + parkingPlace +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(numbers, vehicle.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }

    abstract void validateVehicle(int size);
}

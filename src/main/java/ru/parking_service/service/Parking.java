package ru.parking_service.service;

import java.util.List;
import ru.parking_service.model.Vehicle;

public interface Parking {
    void placeVehicle(Vehicle vehicle);

    Vehicle removeVehicle(Vehicle vehicle);

    <T extends Vehicle> List<Vehicle> getVehicleList(Class<T> type);
}

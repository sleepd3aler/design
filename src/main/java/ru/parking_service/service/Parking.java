package ru.parking_service.service;

import java.util.List;
import java.util.function.Predicate;
import ru.parking_service.model.Vehicle;

public interface Parking {
    void placeVehicle(Vehicle vehicle);

    Vehicle removeVehicle(Vehicle vehicle);

    List<Vehicle> getVehicleList(Predicate<Integer> condition);
}

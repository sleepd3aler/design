package ru.parking_service.service;

import java.util.ArrayList;
import java.util.List;
import ru.parking_service.model.Vehicle;

public class ParkingImpl implements Parking {
    private int possibleCarPlaces;
    private int possibleTruckPlaces;
    private int amountCars;
    private int amountTrucks;
    private List<Vehicle> carPlaces = new ArrayList<>();

    public ParkingImpl(int possibleCarPlacesPlaces, int possibleTruckPlaces) {
        this.possibleCarPlaces = possibleCarPlacesPlaces;
        this.possibleTruckPlaces = possibleTruckPlaces;
    }

    @Override
    public void placeVehicle(Vehicle vehicle) {

    }

    @Override
    public Vehicle removeVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public <T extends Vehicle> List<Vehicle> getVehicleList(Class<T> type) {
        return List.of();
    }
}

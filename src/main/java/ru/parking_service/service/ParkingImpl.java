package ru.parking_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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
    public List<Vehicle> getVehicleList(Predicate<Integer> condition) {
        return List.of();
    }
}

package ru.parking_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.parking_service.exceptions.ParkingException;
import ru.parking_service.model.Type;
import ru.parking_service.model.Vehicle;

import static ru.parking_service.constants.Constants.*;
import static ru.parking_service.model.Type.CAR;

public class ParkingImpl implements Parking {
    private final int possibleCarPlaces;
    private final int possibleTruckPlaces;
    private int amountCars;
    private int amountTrucks;
    private final int carZoneEnd;
    private final int truckZoneEnd;
    private final List<Vehicle> carPlaces = new ArrayList<>();

    public ParkingImpl(int possibleCarPlacesPlaces, int possibleTruckPlaces) {
        this.possibleCarPlaces = possibleCarPlacesPlaces;
        this.possibleTruckPlaces = possibleTruckPlaces;
        this.carZoneEnd = possibleCarPlacesPlaces;
        this.truckZoneEnd = carZoneEnd + this.possibleTruckPlaces;
        setParkingPlaces();
    }

    @Override
    public void placeVehicle(Vehicle vehicle) {
        boolean placed = false;
        if (vehicle.getType() == CAR) {
            if (checkFreePlaces(freeSlots -> freeSlots < possibleCarPlaces, amountCars)
                    && placedInZone(vehicle, CAR_ZONE_START, carZoneEnd)) {
                amountCars++;
                placed = true;
            }
        } else {
            if (checkFreePlaces(freeSlots -> freeSlots < possibleTruckPlaces, amountTrucks)
                    && placedInZone(vehicle, carZoneEnd, truckZoneEnd)) {
                amountTrucks++;
                placed = true;
            } else if (placeTruckToCarZone(vehicle)) {
                placed = true;
            }
        }
        if (!placed) {
            throw new ParkingException(
                    "No more space for : " + vehicle.getType() + " with numbers: " + vehicle.getNumbers()
            );
        }
    }

    @Override
    public Vehicle removeVehicle(Vehicle vehicle) {
        if (vehicle != null && carPlaces.contains(vehicle)) {
            carPlaces.replaceAll(v -> (v != null && v.equals(vehicle) ? null : v));
            if (vehicle.getSize() == CAR_SIZE) {
                amountCars--;
            }
            if (vehicle.getSize() > CAR_SIZE) {
                amountTrucks--;
            }
            return vehicle;
        }
        return null;
    }

    @Override
    public List<Vehicle> getVehicleList(Type type) {
        List<Vehicle> result = new ArrayList<>();
        carPlaces.stream().filter(vehicle -> vehicle != null && vehicle.getType().equals(type)).distinct().forEach(result::add);
        return result;
    }

    private boolean placedInZone(Vehicle vehicle, int start, int end) {
        int freePlace = getFreeSlot(start, end);
        if (freePlace != NOT_FOUND) {
            carPlaces.set(freePlace, vehicle);
            return true;
        }
        return false;
    }

    private boolean placeTruckToCarZone(Vehicle vehicle) {
        int start = findFreeSlots(vehicle.getSize());
        if (start == NOT_FOUND) {
            return false;
        }
        for (int i = start; i < start + vehicle.getSize(); i++) {
            carPlaces.set(i, vehicle);
        }
        return true;
    }

    private int findFreeSlots(int size) {
        for (int slot = CAR_ZONE_START; slot < carZoneEnd; slot++) {
            boolean hasPlace = true;
            if (slot + size > possibleCarPlaces) {
                break;
            }
            for (int j = slot; j < slot + size; j++) {
                if (carPlaces.get(j) != null) {
                    hasPlace = false;
                    break;
                }
            }
            if (hasPlace) {
                return slot;
            }
        }
        return NOT_FOUND;
    }

    private int getFreeSlot(int start, int end) {
        for (int i = start; i < end; i++) {
            if (carPlaces.get(i) == null) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private boolean checkFreePlaces(Predicate<Integer> condition, int amountVehicle) {
        return condition.test(amountVehicle);
    }

    private void setParkingPlaces() {
        for (int i = 0; i < possibleCarPlaces + possibleTruckPlaces; i++) {
            carPlaces.add(null);
        }
    }
}

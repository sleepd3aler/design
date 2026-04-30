package ru.parking_service.service;

import java.util.ArrayList;
import java.util.List;
import ru.parking_service.exceptions.ParkingException;
import ru.parking_service.model.Type;
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
        setParkingPlaces();
    }

    @Override
    public void placeVehicle(Vehicle vehicle) {
        boolean placed;
        if (vehicle.getSize() == 1) {
            placed = placeCar(vehicle);
        } else {
            placed = placeTruck(vehicle) || placeTruckToCarZone(vehicle);
        }
        if (!placed) {
            throw new ParkingException();
        }
    }

    @Override
    public Vehicle removeVehicle(Vehicle vehicle) {
        if (vehicle != null && carPlaces.contains(vehicle)) {
            carPlaces.replaceAll(v -> (v != null && v.equals(vehicle) ? null : v));
            if (vehicle.getSize() == 1) {
                amountCars--;
            }
            if (vehicle.getSize() > 1) {
                amountTrucks--;
            }
            return vehicle;
        }
        return null;
    }

    @Override
    public List<Vehicle> getVehicleList(Type type) {
        List<Vehicle> result = new ArrayList<>();
        carPlaces.stream()
                .filter(vehicle -> vehicle != null && vehicle.getType().equals(type))
                .distinct()
                .forEach(result::add);
        return result;
    }

    private boolean placeTruckToCarZone(Vehicle vehicle) {
        int start = findFreeSpots(vehicle.getSize());
        if (start == -1) {
            return false;
        }
        for (int i = start; i < start + vehicle.getSize(); i++) {
            carPlaces.set(i, vehicle);
        }
        return true;
    }

    private int findFreeSpots(int size) {
        int carZoneStart = 0;
        int carZoneEnds = possibleCarPlaces - 1;
        for (int i = carZoneStart; i <= carZoneEnds; i++) {
            boolean hasPlace = true;
            if (i + size > possibleCarPlaces) {
                break;
            }
            for (int j = i; j < i + size; j++) {
                if (carPlaces.get(j) != null) {
                    hasPlace = false;
                    break;
                }
            }
            if (hasPlace) {
                return i;
            }
        }
        return -1;
    }

    private boolean placeCar(Vehicle vehicle) {
        if (!checkCarPlaces()) {
            return false;
        }
        int freePlace = getFreeCarSlot();
        if (freePlace == -1) {
            return false;
        }
        carPlaces.set(freePlace, vehicle);
        amountCars++;
        return true;
    }

    private boolean placeTruck(Vehicle vehicle) {
        if (!checkTruckPlaces()) {
            return false;
        }
        int position = getFreeSlotForTruck();
        if (position == -1) {
            return false;
        }
        carPlaces.set(position, vehicle);
        amountTrucks++;
        return true;
    }

    private int getFreeSlotForTruck() {
        int truckZoneStart = possibleCarPlaces;
        int truckZoneEnd = possibleCarPlaces + possibleTruckPlaces - 1;
        for (int i = truckZoneStart; i <= truckZoneEnd; i++) {
            if (carPlaces.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkTruckPlaces() {
        return amountTrucks < possibleTruckPlaces;
    }

    private boolean checkCarPlaces() {
        return amountCars < possibleCarPlaces;
    }

    private int getFreeCarSlot() {
        int carZoneEnd = possibleCarPlaces;
        for (int i = 0; i < carZoneEnd; i++) {
            if (carPlaces.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private void setParkingPlaces() {
        for (int i = 0; i < possibleCarPlaces + possibleTruckPlaces; i++) {
            carPlaces.add(null);
        }
    }
}

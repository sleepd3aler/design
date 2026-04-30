package ru.parking_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.parking_service.exceptions.ParkingException;
import ru.parking_service.model.Car;
import ru.parking_service.model.Truck;
import ru.parking_service.model.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.parking_service.model.Type.CAR;
import static ru.parking_service.model.Type.TRUCK;

class ParkingImplTest {
    private Vehicle car1;
    private Vehicle car2;
    private Vehicle car3;
    private Vehicle truck1;
    private Vehicle truck2;
    private Vehicle truck3;
    private Parking parking;

    @BeforeEach
    void setup() {
        car1 = new Car("Toyota", "12345", "Blue", 1);
        car2 = new Car("Hyundai", "22345", "Black", 1);
        car3 = new Car("Subaru", "32345", "Blue", 1);
        truck1 = new Truck("Mercedes", "42345", "White", 2);
        truck2 = new Truck("GAZ", "42345", "White", 2);
        truck3 = new Truck("Monster-Truck", "42345", "White", 5);
        parking = new ParkingImpl(5, 5);
    }

    @Test
    void whenAdd3CarsAnd2TrucksThenParkingContainsThem() {
        parking.placeVehicle(car1);
        parking.placeVehicle(car2);
        parking.placeVehicle(car3);
        parking.placeVehicle(truck1);
        parking.placeVehicle(truck2);
        assertThat(parking.getVehicleList(CAR)).containsOnly(car1, car2, car3);
        assertThat(parking.getVehicleList(TRUCK)).containsOnly(truck1, truck2);
    }

    @Test
    void whenAddTruckToBusyParkingThenTruckMovedToCarListAndParkingContainsIt() {
        parking.placeVehicle(truck3);
        parking.placeVehicle(truck2);
        assertThat(parking.getVehicleList(TRUCK)).contains(truck3, truck2);
    }

    @Test
    void whenAddVehicleAndParkingHaveNoSpaceThenExceptionThrown() {
        parking = new ParkingImpl(5, 2);
        parking.placeVehicle(car1);
        parking.placeVehicle(car2);
        parking.placeVehicle(car3);
        parking.placeVehicle(truck1);
        parking.placeVehicle(truck3);
        parking.placeVehicle(truck2);
        assertThatThrownBy(() -> parking.placeVehicle(new Truck("test", "999", "grey", 2)))
                .isInstanceOf(ParkingException.class);
        assertThatThrownBy(() -> parking.placeVehicle(
                new Car("Blablacar", "666", "pink", 1)
        ))
                .isInstanceOf(ParkingException.class);
    }

    @Test
    void whenRemoveVehicleThenParkingDoesntContainsIt() {
        parking.placeVehicle(car1);
        parking.placeVehicle(car2);
        parking.placeVehicle(car3);
        parking.placeVehicle(truck1);
        parking.placeVehicle(truck2);
        assertThat(parking.removeVehicle(car1)).isEqualTo(car1);
    }

    @Test
    void whenRemoveTruckFromFullParkingThenVehiclesCanBeAdded() {
        parking = new ParkingImpl(4, 0);
        parking.placeVehicle(car1);
        parking.placeVehicle(car2);
        parking.placeVehicle(car3);
        parking.removeVehicle(car3);
        parking.placeVehicle(truck2);
        assertThat(parking.getVehicleList(TRUCK)).containsOnly(truck2);
    }

    @Test
    void whenRemoveFewCarsInARowThenTruckPlacedSuccessful() {
        parking = new ParkingImpl(6, 5);
        parking.placeVehicle(car1);
        parking.placeVehicle(car2);
        parking.placeVehicle(car3);
        parking.placeVehicle(truck1);
        parking.placeVehicle(truck2);
        parking.removeVehicle(car2);
        parking.removeVehicle(car3);
        parking.placeVehicle(truck3);
        assertThat(parking.getVehicleList(TRUCK)).containsOnly(truck1, truck2, truck3);
    }
}
package ru.parking_service.exceptions;

public class ParkingException extends RuntimeException {
    public ParkingException(String message) {
        super(message);
    }

    public ParkingException() {
    }
}

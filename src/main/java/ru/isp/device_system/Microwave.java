package ru.isp.device_system;

import java.time.LocalTime;

public class Microwave implements Kitchen, Timeable {
    private LocalTime time;
    private int degrees;

    @Override
    public void turnOn() {
        System.out.println("Beep - beep");

    }

    @Override
    public void turnOff() {
        System.out.println("***R2D2 sounds***");
    }

    @Override
    public void setTemperature(int degrees) {
        this.degrees = degrees;
    }

    @Override
    public void setTime(LocalTime time) {
        this.time = time;
    }

}

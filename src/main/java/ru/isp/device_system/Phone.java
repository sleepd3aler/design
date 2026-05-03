package ru.isp.device_system;

import java.time.LocalTime;

public class Phone implements Callable, Timeable, Input {
    private String numbers;
    private LocalTime currentTime;

    @Override
    public void turnOn() {
        System.out.println("Device is working.");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off...");
    }

    @Override
    public void in(String data) {
        this.numbers = data;
    }

    @Override
    public void call() {
        System.out.println("Calling : " + this.numbers);
    }

    @Override
    public void setTime(LocalTime time) {
        this.currentTime = time;
    }
}

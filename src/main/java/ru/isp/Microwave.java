package ru.isp;

import java.time.LocalTime;

public class Microwave implements Device {
    private LocalTime time;
    private int degrees;

    @Override
    public void in(String data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void call() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTemperature(int degrees) {
        this.degrees = degrees;
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public void play() {
        throw new UnsupportedOperationException();
    }
}

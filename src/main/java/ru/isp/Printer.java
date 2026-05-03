package ru.isp;

import java.time.LocalTime;

public class Printer implements Device {
    private String data;

    @Override
    public void in(String data) {
        this.data = data;
    }

    @Override
    public void call() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTemperature(int degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print() {
        System.out.println("Printing: " + this.data);
    }

    @Override
    public void setTime(LocalTime time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void play() {
        throw new UnsupportedOperationException();
    }
}

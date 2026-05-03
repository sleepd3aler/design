package ru.isp;

import java.time.LocalTime;

public class Phone implements Device {
    private String numbers;
    private LocalTime currentTime;

    @Override
    public void in(String data) {
        this.numbers = data;
    }

    @Override
    public void call() {
        System.out.println("Calling : " + this.numbers);
    }

    @Override
    public void setTemperature(int degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(LocalTime time) {
        this.currentTime = time;
    }

    @Override
    public void play() {
        throw new UnsupportedOperationException();
    }
}

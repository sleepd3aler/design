package ru.isp;

import java.time.LocalTime;

public class MusicPlayer implements Device {
    private String music;

    @Override
    public void in(String data) {
        this.music = data;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(LocalTime time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void play() {
        System.out.println("Playing track: " + this.music);
    }
}

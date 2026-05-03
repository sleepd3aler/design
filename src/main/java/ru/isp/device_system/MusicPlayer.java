package ru.isp.device_system;

public class MusicPlayer implements Playable, Input {
    private String music;

    @Override
    public void turnOn() {
        System.out.println("Hello! Let`s have fun");
    }

    @Override
    public void turnOff() {
        System.out.println("...");
    }

    @Override
    public void in(String data) {
        this.music = data;
    }

    @Override
    public void play() {
        System.out.println("Playing track: " + this.music);
    }
}

package ru.isp.device_system;

public class Printer implements Printable, Input {
    private String data;

    @Override
    public void turnOn() {
        System.out.println("Stand by...");
    }

    @Override
    public void turnOff() {
        System.out.println("...");
    }

    @Override
    public void in(String data) {
        this.data = data;
    }

    @Override
    public void print() {
        System.out.println("Printing: " + this.data);
    }

}

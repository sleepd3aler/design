package ru.ood.srp;

public class SequenceOutput implements Output {
    @Override
    public void printMessage(String message) {
        System.out.println("The sequence is: " + message);
    }
}

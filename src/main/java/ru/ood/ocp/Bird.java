package ru.ood.ocp;

public class Bird {
    public String fly() {
        return "Im` flying";
    }

    private static class Airplane extends Bird {
        @Override
        public String fly() {
            return "Not only birds can fly";
        }
    }
}

package ru.isp;

public interface Figure {
    double perimeter();

    double square();

    double volume();

    interface Figure2D {
        double perimeter();

        double square();
    }

    interface Figure3D {
        double volume();
    }

}

package ru.isp;

import java.time.LocalTime;

public interface Device {
    void in(String data);

    void call();

    void setTemperature(int degrees);

    void print();

    void setTime(LocalTime time);

    void play();

}

package ru.isp.device_system;

import java.time.LocalTime;

public interface Timeable extends Device {
    void setTime(LocalTime time);
}

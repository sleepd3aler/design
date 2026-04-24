package ru.product_quality_service.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServiceUtil {

    public static long calculate(LocalDateTime created, LocalDateTime expire) {
        long totalDays = ChronoUnit.HOURS.between(created, expire);
        long passedDays = ChronoUnit.HOURS.between(created, LocalDateTime.now());
        return (passedDays * 100) / totalDays;

    }
}

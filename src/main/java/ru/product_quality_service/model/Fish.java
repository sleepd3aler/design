package ru.product_quality_service.model;

import java.time.LocalDateTime;

public class Fish extends Food {
    public Fish(String name, int price, int discount, LocalDateTime createDate, LocalDateTime expiryDate) {
        super(name, price, discount, createDate, expiryDate);
    }
}

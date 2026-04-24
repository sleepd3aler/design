package ru.product_quality_service.model;

import java.time.LocalDateTime;

public class Milk extends Food {
    public Milk(String name, int price, int discount, LocalDateTime createDate, LocalDateTime expiryDate) {
        super(name, price, discount, createDate, expiryDate);
    }

}

package ru.product_quality_service.storage;

import ru.product_quality_service.model.Food;

import static ru.product_quality_service.utils.ServiceUtil.*;

public class Trash extends AbstractStore {
    public Trash() {
        super(100);
    }

    @Override
    public boolean inDate(Food food, int possiblePercent) {
        long expiryPercent = calculate(food.getCreateDate(), food.getExpiryDate());

        return expiryPercent >= possiblePercent;
    }
}

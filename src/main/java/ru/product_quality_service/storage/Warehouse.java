package ru.product_quality_service.storage;

import ru.product_quality_service.model.Food;
import static ru.product_quality_service.utils.ServiceUtil.*;

public class Warehouse extends AbstractStore {

    public Warehouse(int percent) {
        super(percent);
    }

    @Override
    public boolean inDate(Food food, int percent) {
        long expiryPercent = calculate(food.getCreateDate(), food.getExpiryDate());
        return expiryPercent < percent;
    }
}

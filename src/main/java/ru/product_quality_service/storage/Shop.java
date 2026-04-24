package ru.product_quality_service.storage;

import ru.product_quality_service.model.Food;

import static ru.product_quality_service.utils.ServiceUtil.calculate;

public class Shop extends AbstractStore {

    public Shop(int percent) {
        super(percent);
    }

    @Override
    public Food addProduct(Food food) {
        if (inDate(food, getPossiblePercent())) {
            if (isNearlyExpired(food)) {
                makeDiscount(food);
            }
        }
        return super.addProduct(food);
    }

    @Override
    public boolean inDate(Food food, int percent) {
        long expiryPercent = calculate(food.getCreateDate(), food.getExpiryDate());
        return expiryPercent >= percent && expiryPercent < 100;
    }

    private void makeDiscount(Food food) {
        food.setDiscount(20);
        food.setPrice((int) (food.getPrice() * (1 - (food.getDiscount() / 100.0))));
    }

    private boolean isNearlyExpired(Food food) {
        long expiryPercent = calculate(food.getCreateDate(), food.getExpiryDate());
        return expiryPercent > 76 && expiryPercent < 100;
    }
}

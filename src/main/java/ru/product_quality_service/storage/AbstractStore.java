package ru.product_quality_service.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import ru.product_quality_service.model.Food;

public abstract class AbstractStore implements Store {
    private static int ids = 1;
    private final Map<Integer, Food> storage = new HashMap<>();
    private final int possiblePercent;

    public AbstractStore(int possiblePercent) {
        this.possiblePercent = possiblePercent;
    }

    @Override
    public Food addProduct(Food food) {
        if (inDate(food, possiblePercent)) {
            food.setId(ids++);
            storage.put(food.getId(), food);
        }
        return food;
    }

    @Override
    public Optional<Food> deleteProduct(Food food) {
        return Optional.ofNullable(storage.remove(food.getId()));
    }

    @Override
    public Optional<Food> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<Food> findByName(String name) {
        return storage.values()
                .stream()
                .filter(elem -> elem.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Food> findAll() {
        return storage.values().stream().toList();
    }

    @Override
    public abstract boolean inDate(Food food, int percent);

    public int getPossiblePercent() {
        return possiblePercent;
    }
}

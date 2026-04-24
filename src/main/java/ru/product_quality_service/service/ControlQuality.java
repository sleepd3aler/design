package ru.product_quality_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.product_quality_service.model.Food;
import ru.product_quality_service.storage.Store;

public class ControlQuality implements QualityService {
    private final List<Store> stores = new ArrayList<>();

    @Override
    public void addStore(Store store) {
        stores.add(store);
    }

    @Override
    public void removeStore(Store store) {
        stores.remove(store);
    }

    @Override
    public Food addProduct(Food food) {
        stores.forEach(store -> store.addProduct(food));
        Optional<Food> added = findById(food.getId());
        return added.orElseThrow(() -> new IllegalArgumentException("Product doesnt matches"));
    }

    @Override
    public Optional<Food> deleteProduct(Food food) {
        Optional<Food> deleted = findById(food.getId());
        stores.forEach(store -> store.deleteProduct(food));
        return deleted;
    }

    @Override
    public Optional<Food> findById(int id) {
        for (Store store : stores) {
            Optional<Food> res = store.findById(id);
            if (res.isPresent()) {
                return res;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Food> findByName(String name) {
        for (Store store : stores) {
            Optional<Food> res = store.findByName(name);
            if (res.isPresent()) {
                return res;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Food> findAll() {
        List<Food> res = new ArrayList<>();
        stores.forEach(store -> res.addAll(store.findAll()));
        return res;
    }
}

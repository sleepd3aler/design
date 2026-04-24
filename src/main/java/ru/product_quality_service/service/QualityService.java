package ru.product_quality_service.service;

import java.util.List;
import java.util.Optional;
import ru.product_quality_service.model.Food;
import ru.product_quality_service.storage.Store;

public interface QualityService {
    void addStore(Store store);

    void removeStore(Store store);

    Food addProduct(Food food);

    Optional<Food> deleteProduct(Food food);

    Optional<Food> findById(int id);

    Optional<Food> findByName(String name);

    List<Food> findAll();
}

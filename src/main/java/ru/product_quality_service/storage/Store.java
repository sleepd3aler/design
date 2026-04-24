package ru.product_quality_service.storage;

import java.util.List;
import java.util.Optional;
import ru.product_quality_service.model.Food;

public interface Store {

    Food addProduct(Food food);

    Optional<Food> deleteProduct(Food food);

    Optional<Food> findById(int id);

    Optional<Food> findByName(String name);

    List<Food> findAll();

    boolean inDate(Food food, int percent);

}

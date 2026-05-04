package ru.dip;

import java.util.ArrayList;
import java.util.List;
import ru.product_quality_service.model.Food;
/** 2)
 *  Нарушение DIP выражено тем, что Сервис зависит от конкретной реализации -> InMemProductStorage(Нужно
 *  сделать абстрактное хранилище и зависеть от абстракции */

public class ProductService {
    private InMemProductStorage storage;

    void save(Food food) {
        storage.save(food);
    }

    class InMemProductStorage {
        /** 3)
         * Так же и здесь нарушение DIP, так как хранилище должно работать с абстракцией Product - а не только с Food */
        private List<Food> storage = new ArrayList<>();

        void save(Food food) {
            storage.add(food);
        }

    }
}

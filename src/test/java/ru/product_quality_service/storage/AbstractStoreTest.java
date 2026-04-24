package ru.product_quality_service.storage;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.product_quality_service.model.Fish;
import ru.product_quality_service.model.Food;
import ru.product_quality_service.model.Meat;
import ru.product_quality_service.model.Milk;

import static org.assertj.core.api.Assertions.*;

abstract class AbstractStoreTest {
    protected Store store;
    protected Food freshProduct;
    protected Food normalProduct;
    protected Food discountProduct;
    protected Food expiredProduct;

    abstract Store createStore();

    @BeforeEach
    void setUp() {
        store = createStore();
        // 0%
        freshProduct = new Milk("NeMoloko",
                190, 0,
                LocalDateTime.of(2026, 4, 23, 15, 0),
                LocalDateTime.of(2026, 5, 3, 15, 0));
        //50%
        normalProduct = new Fish("Tuna",
                3000, 0,
                LocalDateTime.of(2026, 4, 19, 15, 0),
                LocalDateTime.of(2026, 4, 29, 15, 0));
        //90%
        discountProduct = new Fish("Salmon",
                3000, 0,
                LocalDateTime.of(2026, 4, 15, 15, 0),
                LocalDateTime.of(2026, 4, 25, 15, 0));
        //110%
        expiredProduct = new Meat("Ribeye",
                190, 0,
                LocalDateTime.of(2026, 1, 13, 15, 0),
                LocalDateTime.of(2026, 4, 23, 15, 0));
    }

    @Test
    void whenDeleteNormalThenShopDoesntContainsIt() {
        store.addProduct(normalProduct);
        store.deleteProduct(normalProduct);
        assertThat(store.findAll()).isEmpty();
    }

    @Test
    void whenFindByIdThenExpectedResult() {
        store.addProduct(normalProduct);
        Food expected = new Fish("Tuna",
                3000, 0,
                LocalDateTime.of(2026, 4, 19, 15, 0),
                LocalDateTime.of(2026, 4, 29, 15, 0));
        expected.setId(normalProduct.getId());
        assertThat(store.findById(normalProduct.getId()).get()).isEqualTo(expected);
    }

    @Test
    void whenFindByNameThenExpectedResult() {
        store.addProduct(normalProduct);
        Food expected = new Fish("Tuna",
                3000, 0,
                LocalDateTime.of(2026, 4, 19, 15, 0),
                LocalDateTime.of(2026, 4, 29, 15, 0));
        expected.setId(normalProduct.getId());
        assertThat(store.findByName(normalProduct.getName()).get()).isEqualTo(expected);
    }

}
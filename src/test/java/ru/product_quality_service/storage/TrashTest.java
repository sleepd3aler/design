package ru.product_quality_service.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TrashTest extends AbstractStoreTest {
    @BeforeEach
    void setUpProducts() {
        normalProduct.setCreateDate(expiredProduct.getCreateDate());
        normalProduct.setExpiryDate(expiredProduct.getExpiryDate());
    }

    @Override
    Store createStore() {
        return new Trash();
    }

    @Test
    void whenAddExpiredProductsThenTrashContainsThem() {
        store.addProduct(expiredProduct);
        store.addProduct(freshProduct);
        store.addProduct(normalProduct);
        assertThat(store.findAll()).contains(expiredProduct, normalProduct);
    }
}
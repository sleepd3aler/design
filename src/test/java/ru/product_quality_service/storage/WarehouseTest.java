package ru.product_quality_service.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest extends AbstractStoreTest {
    @BeforeEach
    void setUpProducts() {
    normalProduct.setCreateDate(freshProduct.getCreateDate());
    normalProduct.setExpiryDate(freshProduct.getExpiryDate());
    }

    @Override
    Store createStore() {
        return new Warehouse(25);
    }

    @Test
    void whenAddLegalProductThenWareHouseContainsThem() {
        store.addProduct(freshProduct);
        store.addProduct(normalProduct);
        store.addProduct(expiredProduct);
        assertThat(store.findAll()).contains(freshProduct, normalProduct);
    }
}
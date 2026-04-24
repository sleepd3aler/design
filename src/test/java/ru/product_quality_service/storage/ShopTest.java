package ru.product_quality_service.storage;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.product_quality_service.model.Food;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest extends AbstractStoreTest {
    @Override
    Store createStore() {
        return new Shop(25);
    }

    @Test
    void whenAddLegalProductsThenShopContainsThem() {
        store.addProduct(freshProduct);
        store.addProduct(normalProduct);
        store.addProduct(expiredProduct);
        List<Food> res = store.findAll();
        assertThat(res).containsOnly(normalProduct);
    }

    @Test
    void whenAddAlmostExpiredProductThenProductDiscounted() {
        int beforeDiscount = discountProduct.getPrice();
        store.addProduct(discountProduct);
        assertThat(discountProduct.getPrice()).isLessThan(beforeDiscount);
    }

}
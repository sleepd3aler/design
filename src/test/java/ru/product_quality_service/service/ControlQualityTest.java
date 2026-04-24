package ru.product_quality_service.service;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.product_quality_service.model.Fish;
import ru.product_quality_service.model.Food;
import ru.product_quality_service.model.Meat;
import ru.product_quality_service.model.Milk;
import ru.product_quality_service.storage.Shop;
import ru.product_quality_service.storage.Store;
import ru.product_quality_service.storage.Trash;
import ru.product_quality_service.storage.Warehouse;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    private QualityService service;
    private Store shop;
    private Store warehouse;
    private Store trash;
    private Food freshProduct;
    private Food normalProduct;
    private Food discountProduct;
    private Food expiredProduct;

    @BeforeEach
    void setUp() {
        service = new ControlQuality();
        shop = new Shop(25);
        warehouse = new Warehouse(25);
        trash = new Trash();
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
        service.addStore(shop);
        service.addStore(warehouse);
        service.addStore(trash);
    }

    @Test
    void whenProductsAddedThenEveryStoreContainsTheirProduct() {
        service.addProduct(freshProduct);
        service.addProduct(normalProduct);
        service.addProduct(discountProduct);
        service.addProduct(expiredProduct);
        assertThat(shop.findAll()).containsOnly(normalProduct, discountProduct);
        assertThat(warehouse.findAll()).containsOnly(freshProduct);
        assertThat(trash.findAll()).containsOnly(expiredProduct);
    }

    @Test
    void whenDeleteProductThenStoreDoesntContainsIt() {
        service.addProduct(freshProduct);
        service.addProduct(normalProduct);
        service.addProduct(discountProduct);
        service.addProduct(expiredProduct);
        service.deleteProduct(freshProduct);
        service.deleteProduct(normalProduct);
        service.deleteProduct(discountProduct);
        service.deleteProduct(expiredProduct);
        assertThat(shop.findAll()).isEmpty();
        assertThat(warehouse.findAll()).isEmpty();
        assertThat(trash.findAll()).isEmpty();
    }

    @Test
    void whenProductExpiredOn25PercentsThenShopContainsIt() {
        Food food = new Food("Ramen", 690, 0,
                LocalDateTime.of(2026, 4, 23, 15, 0),
                LocalDateTime.of(2026, 4, 27, 15, 0));
        service.addProduct(food);
        assertThat(shop.findAll()).contains(food);
        assertThat(warehouse.findAll()).doesNotContain(food);
    }

    @Test
    void whenFindByIdThenExpectedResult() {
        service.addProduct(freshProduct);
        service.addProduct(normalProduct);
        service.addProduct(discountProduct);
        service.addProduct(expiredProduct);
        Food expected = new Fish("Salmon",
                3000, 0,
                LocalDateTime.of(2026, 4, 15, 15, 0),
                LocalDateTime.of(2026, 4, 25, 15, 0));
        expected.setId(discountProduct.getId());
        Food res = service.findById(discountProduct.getId()).get();
        assertThat(res).isEqualTo(expected);
    }

    @Test
    void whenFindByNameThenExpectedResult() {
        service.addProduct(freshProduct);
        service.addProduct(normalProduct);
        service.addProduct(discountProduct);
        service.addProduct(expiredProduct);
        Food expected = new Fish("Salmon",
                3000, 0,
                LocalDateTime.of(2026, 4, 15, 15, 0),
                LocalDateTime.of(2026, 4, 25, 15, 0));
        expected.setId(discountProduct.getId());
        Food res = service.findByName(discountProduct.getName()).get();
        assertThat(res).isEqualTo(expected);
    }

    @Test
    void whenFindAllThenResContainsAllAddedProducts() {
        service.addProduct(freshProduct);
        service.addProduct(normalProduct);
        service.addProduct(discountProduct);
        service.addProduct(expiredProduct);
        List<Food> expected = List.of(freshProduct, normalProduct, discountProduct, expiredProduct);
        assertThat(service.findAll()).containsAll(expected);
    }

    @Test
    void whenServiceDoesntContainsShopAndProductDoesntMatchAnyStorageThenExceptionThrown() {
        service = new ControlQuality();
        service.addStore(trash);
        service.addStore(warehouse);
        service.addProduct(freshProduct);
        assertThatThrownBy(() -> service.addProduct(normalProduct))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Product doesnt matches");
    }
}
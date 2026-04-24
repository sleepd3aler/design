package ru.product_quality_service.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Food {

    private int id;

    private String name;

    private int price;

    private int discount;

    private LocalDateTime createDate;

    private LocalDateTime expiryDate;

    public Food(String name, int price, int discount, LocalDateTime createDate, LocalDateTime expiryDate) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        validateFood();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", expiryDate=" + expiryDate +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    private void validateFood() {
        if (expiryDate.isBefore(createDate)) {
            throw new IllegalArgumentException("Product cant be expired before creation.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is missing or cant be null.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cant be negative.");
        }

        if (createDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Creation time is incorrect");
        }
    }
}

package ru.dip.order_service.storage;

public interface Repository {
    int saveOrder(String customer, double amount);
}

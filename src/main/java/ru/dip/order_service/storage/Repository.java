package ru.dip.order_service.storage;

import ru.dip.order_service.model.Order;

public interface Repository {
    int saveOrder(Order order);
}

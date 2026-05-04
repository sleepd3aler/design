package ru.dip.order_service.storage;

import java.util.HashMap;
import java.util.Map;
import ru.dip.order_service.model.Order;

public class InMemoryOrderReportRepository implements Repository {
    private int ids = 1;
    private Map<Integer, Order> repository = new HashMap<>();

    @Override
    public int saveOrder(Order order) {
        repository.put(ids, order);
        return ids++;
    }
}

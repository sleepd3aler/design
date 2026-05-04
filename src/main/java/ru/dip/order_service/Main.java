package ru.dip.order_service;

import java.nio.file.Path;
import ru.dip.order_service.model.Order;
import ru.dip.order_service.notifications.ConsoleNotificationSender;
import ru.dip.order_service.notifications.EmailNotificationService;
import ru.dip.order_service.notifications.TelegramNotificationService;
import ru.dip.order_service.services.OrderService;
import ru.dip.order_service.services.ServiceFactory;
import ru.dip.order_service.storage.DryRun;
import ru.dip.order_service.storage.FileReportRepository;
import ru.dip.order_service.storage.InMemoryOrderReportRepository;

public class Main {
    public static void main(String[] args) {
        Order first = new Order("Alex", 30.3);
        Order second = new Order("Mary", 55.335);
        OrderService service = new OrderService(
                new FileReportRepository(Path.of("src/main/java/ru/dip/order_service/orders/orders.txt").toFile()),
                new EmailNotificationService());

        service.placeOrder(first);
        service.placeOrder(second);

        service = ServiceFactory.createService(new ConsoleNotificationSender(), new InMemoryOrderReportRepository());
        service.placeOrder(first);
        service.placeOrder(second);

        service = ServiceFactory.createService(new TelegramNotificationService(), new DryRun());
        service.placeOrder(first);
        service.placeOrder(second);
    }
}

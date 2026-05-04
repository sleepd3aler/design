package ru.dip.order_service.services;

import java.io.File;
import ru.dip.order_service.storage.FileReportRepository;

public class OrderService {
private FileReportRepository fileReportRepository = new FileReportRepository(new File("./orders.txt"));
private EmailNotificationService emailNotificationService = new EmailNotificationService();

void placeOrder(String customer, double amount) {
    fileReportRepository.saveOrder(customer, amount);
    emailNotificationService.sendEmail(fileReportRepository.getLastOrderId());
}
}

package ru.ood.ocp.payment_service.o_service;

import ru.ood.ocp.payment_service.p_service.PaymentService;

public class OrderService {
   private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void createOrder(String paymentType, double amount) {
        System.out.println("Create order");
        paymentService.process(paymentType, amount);
    }

}

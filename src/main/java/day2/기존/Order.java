package day2.기존;

import java.util.List;

public class Order {
    private final String orderId;
    private final String customerName;
    private final List<String> items;
    private final String deliveryAddress;
    private final boolean express;
    private final String paymentMethod;
    private final String specialInstruction;

    public Order(String orderId, String customerName,
                 List<String> items, String deliveryAddress,
                 boolean express, String paymentMethod,
                 String specialInstruction) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.express = express;
        this.paymentMethod = paymentMethod;
        this.specialInstruction = specialInstruction;
    }
}

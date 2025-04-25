package day2.개선;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class OrderNew {
    private final String orderId;
    private final String customerName;
    private final List<String> items;
    private final String deliveryAddress;
    private final boolean express;
    private final String paymentMethod;
    private final String specialInstruction;

    private OrderNew(Builder builder) {
        this.orderId = builder.orderId;
        this.customerName = builder.customerName;
        this.items = builder.items;
        this.deliveryAddress = builder.deliveryAddress;
        this.express = builder.express;
        this.paymentMethod = builder.paymentMethod;
        this.specialInstruction = builder.specialInstruction;
    }

    public static class Builder{
        private final String orderId;
        private final String customerName;
        private List<String> items = new ArrayList<>();
        private String deliveryAddress;
        private boolean express;
        private String paymentMethod;
        private String specialInstruction;

        public Builder(String orderId, String customerName) {
            this.orderId = Objects.requireNonNull(orderId, "OrderId must not be null");
            this.customerName = Objects.requireNonNull(customerName, "CustomerName must not be null");
        }

        public Builder items(List<String> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        public Builder deliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder express(boolean express) {
            this.express = express;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder specialInstruction(String specialInstruction) {
            this.specialInstruction = specialInstruction;
            return this;
        }

        public OrderNew build() {
            validateOrderData();
            return new OrderNew(this);
        }

        private void validateOrderData() {
            if (items.isEmpty()) {
                throw new IllegalArgumentException("items must not be empty");
            }

            if (deliveryAddress == null && !items.isEmpty()) {
                throw new IllegalArgumentException("deliveryAddress must not be empty");
            }
        }
    }

    @Override
    public String toString() {
        return "OrderNew{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", items=" + items +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", express=" + express +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", specialInstruction='" + specialInstruction + '\'' +
                '}';
    }
}

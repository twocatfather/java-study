package day1.실무에서자주발생하는문제예시.개선된예시;

import java.util.Objects;
import java.util.Optional;

class OrderRefactor {
    OrderStatusRefactor status;
    CustomerRefactor customer;

    public OrderStatusRefactor getStatus() {
        return status;
    }

    public CustomerRefactor getCustomer() {
        return customer;
    }
}

enum OrderStatusRefactor {
    COMPLETED
}

class CustomerRefactor {
    String name;

    public String getName() {
        return name;
    }
}

public class OrderProcessor {
    public void process(OrderRefactor order) {
        Objects.requireNonNull(order, "Order cannot be null");

        if (OrderStatusRefactor.COMPLETED.equals(order.getStatus())) {
            // 처리로직
        }

        CustomerRefactor customer = Optional.ofNullable(order.getCustomer())
                .orElseThrow(() -> new IllegalArgumentException("Customer cannot be null"));

        String customerName = customer.getName();

    }
}

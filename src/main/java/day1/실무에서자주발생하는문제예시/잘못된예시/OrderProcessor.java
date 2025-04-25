package day1.실무에서자주발생하는문제예시.잘못된예시;

class Order {
    String status;
    Customer customer;

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }
}

class Customer {
    String name;

    public String getName() {
        return name;
    }
}

public class OrderProcessor {
    public void process(Order order) {
        if (order.getStatus() == "COMPLETED") { // 문자열 비교 오류
            // 처리 로직
        }

        String customerName = order.getCustomer().getName(); // NPE
    }
}

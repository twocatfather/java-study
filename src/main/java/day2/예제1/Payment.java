package day2.예제1;

import java.math.BigDecimal;

enum PaymentStatus {
    COMPLETED,
    PENDING
}

// 1. 결제 인터 페이스 구성
public interface Payment {
    void processPayment(BigDecimal amount);
    PaymentStatus getStatus();
}

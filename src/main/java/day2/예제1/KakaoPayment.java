package day2.예제1;

import java.math.BigDecimal;

public class KakaoPayment implements Payment{
    private PaymentStatus status = PaymentStatus.PENDING;

    @Override
    public void processPayment(BigDecimal amount) {
        status = PaymentStatus.COMPLETED;
    }

    @Override
    public PaymentStatus getStatus() {
        return status;
    }
}

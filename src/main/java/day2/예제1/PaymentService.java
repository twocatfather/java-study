package day2.예제1;

import java.math.BigDecimal;

public class PaymentService {
    public void processPayment(PaymentMethod method, BigDecimal amount) {
        Payment payment = PaymentFactory.createPayment(method);
        payment.processPayment(amount);

        PaymentStatus status = payment.getStatus();
    }
}

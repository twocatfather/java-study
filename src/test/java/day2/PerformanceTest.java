package day2;

import day2.예제1.CreditCardPayment;
import day2.예제1.Payment;
import day2.예제1.PaymentFactory;
import day2.예제1.PaymentMethod;
import org.junit.jupiter.api.Test;

public class PerformanceTest {

    @Test
    void factoryPerformanceTest() {
        // 1. 직접 인스턴스를 생성하는방식
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            Payment payment = new CreditCardPayment();
        }
        long directTime = System.nanoTime() - start;

        // 2. 팩토리 패턴을 활용한 생성
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            Payment payment = PaymentFactory.createPayment(PaymentMethod.CREDIT_CARD);
        }
        long factoryTime = System.nanoTime() - start;

        System.out.printf("Direct instance time: %d ms%n", directTime / 1_000_000);
        System.out.printf("Factory instance time: %d ms%n", factoryTime / 1_000_000);
    }
}

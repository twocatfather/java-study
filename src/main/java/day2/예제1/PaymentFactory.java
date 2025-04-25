package day2.예제1;

// 3. 심플 팩토리
public class PaymentFactory {
    public static Payment createPayment(PaymentMethod method) {
        return switch (method) {
            case CREDIT_CARD -> new CreditCardPayment();
            case KAKAO_PAY -> new KakaoPayment();
            default -> throw new IllegalArgumentException("Unsupported payment method");
        };
    }
}

package day8.결제시스템;

public class PaymentSystemDemo {
    public static void main(String[] args) {
        DefaultPaymentValidator validator = new DefaultPaymentValidator(5000.0);

        ImprovedPaymentGateway toss = new TossPaymentGateway(validator);

        PaymentProcessor creditCardProcessor = new ImprovedCreditCardProcessor(validator, toss);

        try {
            creditCardProcessor.processPayment(1000.0);
            creditCardProcessor.processPayment(-100.0);
        } catch (PaymentValidationException e) {
            System.err.println("결제 실패: " + e.getMessage());
        }

        try {
            creditCardProcessor.processPayment(7000.0);
        } catch (PaymentValidationException e) {
            System.err.println("결제 실패: " + e.getMessage());
        }
    }
}

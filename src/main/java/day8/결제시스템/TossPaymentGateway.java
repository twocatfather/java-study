package day8.결제시스템;

public class TossPaymentGateway implements ImprovedPaymentGateway{
    private final PaymentValidator paymentValidator;

    public TossPaymentGateway(PaymentValidator paymentValidator) {
        this.paymentValidator = paymentValidator;
    }

    @Override
    public boolean processPayment(double amount) {
        if (paymentValidator.validatePayment(amount)) {
            try {
                System.out.println("payment of " + amount + " processed successfully through TossPayments.");
                return true;
            } catch (Exception e) {
                System.err.println("TossPayments payment processing failed.");
                return false;
            }

        } else {
            System.err.println("Invalid payment amount.");
            return false;
        }
    }
}

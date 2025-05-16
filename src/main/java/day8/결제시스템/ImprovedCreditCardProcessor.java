package day8.결제시스템;

public class ImprovedCreditCardProcessor implements PaymentProcessor{
    private final PaymentValidator paymentValidator;
    private final ImprovedPaymentGateway paymentGateway;

    public ImprovedCreditCardProcessor(PaymentValidator paymentValidator, ImprovedPaymentGateway paymentGateway) {
        this.paymentValidator = paymentValidator;
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void processPayment(double amount) throws PaymentValidationException {
        if (paymentValidator.validatePayment(amount)) {
            boolean success = paymentGateway.processPayment(amount);

            if (!success) {
                throw new PaymentValidationException("Payment processing failed.");
            }
        } else {
            throw new PaymentValidationException("Invalid payment amount.");
        }
    }
}

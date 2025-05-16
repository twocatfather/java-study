package day8.결제시스템;

public class PaymentGateway {
    private final PaymentValidator paymentValidator;
    private final PaymentProcessor paymentProcessor;

    public PaymentGateway(PaymentValidator paymentValidator, PaymentProcessor paymentProcessor) {
        this.paymentValidator = paymentValidator;
        this.paymentProcessor = paymentProcessor;
    }

    public void processPayment(double amount) throws PaymentValidationException {
        if (paymentValidator.validatePayment(amount)) {
            try {
                paymentProcessor.processPayment(amount);
                System.out.println("Payment of " + amount + " processed successfully." );
            } catch (Exception e) {
                throw new PaymentValidationException("Payment processing failed.");
            }
        } else {
            throw new RuntimeException("Payment was not successful");
        }
    }
}

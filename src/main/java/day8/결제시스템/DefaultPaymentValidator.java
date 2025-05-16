package day8.결제시스템;

public class DefaultPaymentValidator implements PaymentValidator{
    private static final double DEFAULT_MAX_AMOUNT = 10000.0;

    private final double maxAmount;

    public DefaultPaymentValidator() {
        this(DEFAULT_MAX_AMOUNT);
    }

    public DefaultPaymentValidator(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public boolean validatePayment(double amount) {
        if (amount <= 0) {
            System.out.println("잘못된 결제입니다.");
            return false;
        }

        if (amount > maxAmount) {
            System.out.println("결제 금액이 초과되었습니다.");
            return false;
        }

        return true;
    }
}

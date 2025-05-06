package day5.할인정책시스템;

public class FixedAmountDiscount implements DiscountStrategy{
    private double fixedAmount;

    // 할인 금액을 받아서 검증
    public FixedAmountDiscount(double fixedAmount) {
        if (fixedAmount < 0) {
            throw new IllegalArgumentException("할인 금액은 0 이상이어야 합니다.");
        }
        this.fixedAmount = fixedAmount;
    }

    @Override
    public double calculateDiscountAmount(double price) {
        return Math.min(fixedAmount, price);
    }


}

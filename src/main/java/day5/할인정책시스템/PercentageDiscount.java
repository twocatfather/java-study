package day5.할인정책시스템;

public class PercentageDiscount implements DiscountStrategy {
    private double percentage;

    // 할인율을 받는 생성자 검증

    public PercentageDiscount(double percentage) {
        // 할인율 범위
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("할인율은 0에서 100 사이어야 합니다.");
        }
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscountAmount(double price) {
        return price * (percentage / 100);
    }
}

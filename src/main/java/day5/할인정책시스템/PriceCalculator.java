package day5.할인정책시스템;

public class PriceCalculator {
    private DiscountStrategy discountStrategy;

    // 최종적으로 계산하는 메소드
    // 할인 전략이 설정되지 않은 경우 예외처리
    // 할인 금액 계산
    // 최종가격 = 원래가격 - 할인 금액

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateFinalPrice(double originalPrice) {
        if (discountStrategy == null) {
            throw new IllegalStateException("할인 전략이 설정되지 않았습니다");
        }

        double discount = discountStrategy.calculateDiscountAmount(originalPrice);
        return originalPrice - discount;
    }
}

package day5.할인정책시스템;

public class DiscountDemo {
    public static void main(String[] args) {
        double originalPrice = 100000.0;

        PriceCalculator priceCalculator = new PriceCalculator();

        System.out.println("원래 상품 가격: " + originalPrice + "원");
        System.out.println("비율 할인 적용 (10%)");

        priceCalculator.setDiscountStrategy(new PercentageDiscount(10));
        double priceWithPercentage = priceCalculator.calculateFinalPrice(originalPrice);
        System.out.println("정률 할인 적용 금액: " + priceWithPercentage + "원");

        System.out.println("정액 할인 적용(만원)");
        priceCalculator.setDiscountStrategy(new FixedAmountDiscount(30000));
        double priceWithFixed = priceCalculator.calculateFinalPrice(originalPrice);
        System.out.println("정액 할인 적용 금액: " + priceWithFixed + "원");
    }
}

package day5.결제시스템;

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentStrategy creditCardPayment = new CreditCardPayment("홍길동",
                "1234-5678-9012-3456", "123", "12/34");

        PaymentProcessor processor = new PaymentProcessor(creditCardPayment);

        System.out.println("========= 신용카드 결제 ===========");
        processor.pay(10000);

        // 결제방법을 paypal 변경
        System.out.println("======== PayPal 로 결제 변경 ========");
        PaymentStrategy payPalPayment = new PayPalPayment("test@email.com", "password");
        processor.setPaymentStrategy(payPalPayment);
        processor.pay(10000);

        // kakao
        System.out.println("======== KaKaO 로 결제 변경 ========");
        PaymentStrategy kakao = new KaKaoPayPayment("010-1234-5678", "password");
        processor.setPaymentStrategy(kakao);
        processor.pay(10000);
    }
}

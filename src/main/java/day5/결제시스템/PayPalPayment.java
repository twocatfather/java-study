package day5.결제시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PayPalPayment implements PaymentStrategy{
    private String email;
    private String password;

    @Override
    public void pay(int amount) {
        System.out.println(amount + "원을 PayPal로 결제합니다.");
    }
}

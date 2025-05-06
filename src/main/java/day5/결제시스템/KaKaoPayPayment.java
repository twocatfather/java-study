package day5.결제시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class KaKaoPayPayment implements PaymentStrategy{
    private String phoneNumber;
    private String password;

    @Override
    public void pay(int amount) {
        System.out.println(amount + "원을 카카오페이로 결제합니다.");
    }
}

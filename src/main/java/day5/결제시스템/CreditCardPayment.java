package day5.결제시스템;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *  구체적인 전략 클래스
 *  신용카드를 이용한 결제 방법을 구현
 */
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardPayment implements PaymentStrategy{
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    /**
     * 신용카드로 결제를 진행하는 메소드
     * 실제 구현에서는 카드 정보를 검증하고, 결제 처리를 위한 외부 API 를 호출 할 수 있다.
     * @param amount 결제할 금액
     */
    @Override
    public void pay(int amount) {
        System.out.println(amount + "원을 신용카드로 결제합니다.");
    }
}

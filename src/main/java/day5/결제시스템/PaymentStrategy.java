package day5.결제시스템;

/**
 *  전략패턴에서 '전략'을 담당한다.
 *  다양한 결제 방법들이 구현해야 할 공통 인터페이스를 제공한다.
 */
public interface PaymentStrategy {

    /**
     * 결제를 처리 하는 메소드
     * @param amount 결제할 금액
     */
    void pay(int amount);
}

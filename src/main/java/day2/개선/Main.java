package day2.개선;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderNew test = new OrderNew.Builder("test-1234", "test")
                .items(List.of("a", "b"))
                .deliveryAddress("test")
                .build();

        System.out.println(test.toString());
    }
}

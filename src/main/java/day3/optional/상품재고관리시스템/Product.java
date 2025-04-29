package day3.optional.상품재고관리시스템;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private int stock;
    private BigDecimal price;
}

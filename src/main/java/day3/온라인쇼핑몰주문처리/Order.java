package day3.온라인쇼핑몰주문처리;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String customerName;
    private List<Product> products;
    private LocalDateTime orderDate;


}

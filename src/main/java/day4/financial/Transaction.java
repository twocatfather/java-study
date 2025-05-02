package day4.financial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private LocalDate date;
    private double amount;
    private String type;
    private String category;
    private String description;

    // 월 정보 가져오기
    public Month getMonth() {
        return date.getMonth();
    }

    // 연도 정보 가져오기
    public int getYear() {
        return date.getYear();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %,.0f원 - %s (%s)",
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                type.equals("income") ? "수입" : "지출",
                amount,
                category,
                description);
    }
}

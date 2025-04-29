package day3.optional.학생성적관리시스템;

import lombok.*;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private Map<String, Integer> grades = new HashMap<>();
    private String major;

    public void setGrade(String subject, int grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        grades.put(subject, grade);
    }

    public Optional<Integer> getGrade(String subject) {
        return Optional.ofNullable(grades.get(subject));
    }

    // 학생 전공을 조회하는 메서드 (Null 일 수 있다)
}

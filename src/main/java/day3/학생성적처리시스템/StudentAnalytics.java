package day3.학생성적처리시스템;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentAnalytics {
    public static void main(String[] args) {
        // 테스트를 위한 학생 목록 생성
        List<Student> students = Arrays.asList(
                new Student("John", 95),  // A 등급
                new Student("Jane", 85),  // B 등급
                new Student("Tom", 75),   // C 등급
                new Student("Mary", 65)   // D 등급
        );

        // 1. 평균 점수 계산
        // Stream API 사용하셔서 모든 학생의 점수에 대한 평균을 계산 double
        double averageScore = students.stream()
                .mapToInt(Student::getScore)
                .average()
                .orElse(0.0);

        // 2. 80점 이상인 학생을 필터링
        List<Student> highScoreStudents = students.stream()
                .filter(student -> student.getScore() >= 80)
                .toList();

        // 3. 성적별 학생 수 집계
        Map<String, Long> gradeCount = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.counting()
                ));

        // 추가 예제. 이름의 길이가 3 이하인 학생 이름만 대문자 로 변환
        List<String> shortNames = students.stream()
                .map(Student::getName)
                .filter(name -> name.length() <= 3)
                .map(String::toUpperCase)
                .toList();

    }
}

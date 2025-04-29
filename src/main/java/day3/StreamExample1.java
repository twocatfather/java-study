package day3;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample1 {
    public static void main(String[] args) {
        // list 스트림 생성
        List<String> names = Arrays.asList("Kim", "Lee", "Park", "Choi", "Jeong", "Kang");

        System.out.println("== 원본 데이터 ==");
        System.out.println(names);

        // 필터링: 'K' 로 시작하는 이름만 선택
        List<String> kNames = names.stream()
                .filter(n -> n.startsWith("K"))
                .toList();

        System.out.println("\n== 'K' 로 시작하는 이름 ==");
        System.out.println(kNames);

        // 변환 : map
        List<String> lowerCaseNames = names.stream()
                .map(String::toLowerCase)
                .toList();

        // 통계: 이름의 평균 길이 계산
        double averageLength = names.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
    }
}

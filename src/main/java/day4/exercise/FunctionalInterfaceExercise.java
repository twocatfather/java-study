package day4.exercise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class FunctionalInterfaceExercise {

    // 1. Predicate<T> 사용하기
    // 짝수인지 판별하는 Predicate을 작성
    public void exercise1() {
        // 정수를 받아 짝수인지 판별하는 Predicate을 구현
        Predicate<Integer> isEven = num -> num % 2 == 0;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .toList();

        System.out.println("짝수: " + evenNumbers);
    }

    // 2. Function 사용하기
    // 문자열의 길이를 반환하는 Function을 작성해볼겁니다.
    public void exercise2() {
        Function<String, Integer> getLength = String::length;

        List<String> words = Arrays.asList("Java", "Lambda", "Stream", "Functional Interface");
        List<Integer> lengths = words.stream()
                .map(getLength)
                .toList();

        System.out.println("단어 길이: " + lengths);
    }

    // 3. Consumer
    // 문자열을 대문자로 변환해서 출력하는겁니다.
    public void exercise3() {
        Consumer<String> printUppercase = s -> System.out.println(s.toUpperCase());

        List<String> words = Arrays.asList("Java", "Lambda", "Stream", "Functional Interface");
        words.forEach(printUppercase);
    }

    // 4. supplier
    // 현재시간을 문자열로 반환하는 서플라이어 만들기
    public void exercise4() {
        Supplier<String> getCurrentTime =
                () -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println("현재 시간: " + getCurrentTime.get());
    }

    // 5. BiFunction
    // 두 숫자를 받아서 최대값을 반환하는
    public void exercise5() {
        BiFunction<Integer, Integer, Integer> findMax = Math::max;

        System.out.println("최대값 (10, 20): " + findMax.apply(10, 20));
        System.out.println("최대값 (30, 10): " + findMax.apply(30, 10));
    }

    // 6. 메서드 참조 사용하기
    public void exercise6() {
        List<String> names = Arrays.asList("Kim", "Lee", "Park", "Choi", "Jeong", "Kang");

        names.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("정렬된 이름: " + names);
    }

    // 7. 사용자 정의  함수형 인터페이스 직접 만들어보는것
    @FunctionalInterface
    interface StringTransformer {
        String transform(String input);
    }

    public void exercise7() {
        StringTransformer replace = input -> input.replaceAll("\\s", "");

        // reverse
        StringTransformer reverse = input -> new StringBuilder(input).reverse().toString();

        String text = "Hello Functional World!";

        System.out.println("공백 제거: " + replace.transform(text));
        System.out.println("문자열 뒤집기: " + reverse.transform(text));
    }

    // 8. 여러 함수형 인터페이스 조합
    public void exercise8() {
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "kiwi", "grape", "pear");

        // 5글자 이상인 단어를 필터링하는 Predicate 구성
        Predicate<String> isLongWord = s -> s.length() >= 5;
        // 단어를 대문자로 변환하는 Function 구성
        Function<String, String> toUpperCase = String::toUpperCase;
        // 결과를 출력하는 Consumer
        Consumer<String> printWord = System.out::println;
        // 모두 조합해서 사용해보기 stream
        fruits.stream()
                .filter(isLongWord)
                .map(toUpperCase)
                .forEach(printWord);
    }

    // 9. Function 합성하기
    public void exercise9() {
        // 정수를 제곱하는 Function
        Function<Integer, Integer> square = n -> n * n;
        // 정수에 2를 더하는 Function 구현
        Function<Integer, Integer> addTwo = n -> n + 2;

        // 합성 함수를 생성
        // (10 + 2) * 12
        Function<Integer, Integer> addTwoThenSquare = square.compose(addTwo);
        // (10 * 10) + 2
        Function<Integer, Integer> squareThenAddTwo = square.andThen(addTwo);

        // 테스트
        int number = 5;
        System.out.println("addTwoThenSquare: " + addTwoThenSquare.apply(number));
        System.out.println("squareThenAddTwo: " + squareThenAddTwo.apply(number));
    }

    public static void main(String[] args) {
        FunctionalInterfaceExercise exercise = new FunctionalInterfaceExercise();

//        System.out.println(" 연습 1: Predicate");
//        exercise.exercise1();
//        System.out.println(" 연습 2: Function");
//        exercise.exercise2();
//        System.out.println(" 연습 3: Consumer");
//        exercise.exercise3();
//        System.out.println(" 연습 4: Supplier");
//        exercise.exercise4();
//        System.out.println(" 연습 5: BiFunction");
//        exercise.exercise5();

//        exercise.exercise6();
        exercise.exercise9();
    }
}

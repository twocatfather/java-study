package day1.도서관시스템;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryDemo {
    public static void main(String[] args) {
        // 전통적인 불변 객체 사용
        Book book1 = new Book(
                "978-0134685991",
                "Effective Java",
                "Joshua Bloch",
                LocalDate.of(2018, 1, 6),
                416
        );

        // Record 클래스 사용
        BookRecord bookRecord1 = new BookRecord(
                "978-0134685991",
                "Effective Java",
                "Joshua Bloch",
                LocalDate.of(2018, 1, 6),
                416
        );

        System.out.println("===== 일반 불변 클래스 vs Record 클래스 비교 =====");
        System.out.println("Book toString(): " + book1);
        System.out.println("BookRecord toString(): " + bookRecord1);

        System.out.println("\n===== equals/hashCode 동작 테스트 =====");
        Book book2 = new Book(
                "978-0134685991",
                "Effective Java",
                "Joshua Bloch",
                LocalDate.of(2018, 1, 6),
                416
        );

        BookRecord bookRecord2 = new BookRecord(
                "978-0134685991",
                "Effective Java",
                "Joshua Bloch",
                LocalDate.of(2018, 1, 6),
                416
        );

        System.out.println("book1.equals(book2): " + book1.equals(book2));
        System.out.println("bookRecord1.equals(bookRecord2): " + bookRecord1.equals(bookRecord2));

        // 맵에서의 활용
        Map<Book, String> bookLocationMap = new HashMap<>();
        bookLocationMap.put(book1, "A-1-1");

        Map<BookRecord, String> recordLocationMap = new HashMap<>();
        recordLocationMap.put(bookRecord1, "A-1-1");

        System.out.println("\n===== 맵에서 키로 사용 시 =====");
        System.out.println("bookLocationMap.get(book2): " + bookLocationMap.get(book2));
        System.out.println("recordLocationMap.get(bookRecord2): " + recordLocationMap.get(bookRecord2));

        // record의 추가 메소드 활용
        System.out.println("\n===== Record의 추가 메소드 =====");
        System.out.println("Is recent publication: " + bookRecord1.isRecentPublication());
        System.out.println("Formatted info: " + bookRecord1.getFormattedInfo());

        // 불변성 테스트 (컴파일 에러가 발생하므로 주석 처리)
//         bookRecord1.isbn = "새 ISBN"; // 컴파일 에러
//         book1.setIsbn("새 ISBN"); // 컴파일 에러 (setter가 없음)

        System.out.println("\n===== 컬렉션에서 활용 =====");
        List<BookRecord> library = new ArrayList<>();
        library.add(bookRecord1);
        library.add(new BookRecord("978-0596009205", "Head First Design Patterns", "Eric Freeman",
                LocalDate.of(2004, 10, 1), 694));
        library.add(new BookRecord("978-0321349606", "Java Concurrency in Practice", "Brian Goetz",
                LocalDate.of(2006, 5, 19), 384));

        System.out.println("도서관 목록:");
        for (BookRecord book : library) {
            System.out.println(" - " + book.getFormattedInfo());
        }
    }
}

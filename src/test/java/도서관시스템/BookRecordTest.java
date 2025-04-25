package 도서관시스템;

import day1.도서관시스템.BookRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookRecordTest {

    // given
    // when
    // then

    @Test
    @DisplayName("생성자 테스트 - 정상케이스")
    void constructorValidCase() {
        // given
        String isbn = "978-0123412345";
        String title = "Effective Java";
        String author = "Joshua Bloch";
        LocalDate publishDate = LocalDate.of(2018, 1, 6);
        int pageCount = 416;

        // when
        BookRecord book = new BookRecord(isbn, title, author, publishDate, pageCount);

        // then
        assertEquals(isbn, book.isbn());
        assertEquals(title, book.title());
        assertEquals(author, book.author());
        assertEquals(publishDate, book.publishDate());
        assertEquals(pageCount, book.pageCount());
    }

    @Test
    @DisplayName("생성자 테스트 - ISBN이 null 일 때 예외 발생")
    void constructorNullIsbnThrowsException() {
        // given
        String isbn = null;
        String title = "Effective Java";
        String author = "Joshua Bloch";
        LocalDate publishDate = LocalDate.of(2018, 1, 6);
        int pageCount = 416;

        // when & then
        NullPointerException ex = assertThrows(
                NullPointerException.class,
                () -> new BookRecord(isbn, title, author, publishDate, pageCount)
        );

        assertTrue(ex.getMessage().contains("ISBN must not be null"));
    }
}

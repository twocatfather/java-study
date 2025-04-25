package day1.도서관시스템;

import java.time.LocalDate;
import java.util.Objects;

public record BookRecord(
        String isbn,
        String title,
        String author,
        LocalDate publishDate,
        int pageCount
) {

    public BookRecord {
        Objects.requireNonNull(isbn, "ISBN must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(author, "author must not be null");
        Objects.requireNonNull(publishDate, "publishDate must not be null");

        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be positive");
        }
    }

    public boolean isRecentPublication() {
        return publishDate.isAfter(LocalDate.now().minusYears(5));
    }

    public String getFormattedInfo() {
        return String.format("%s by %s (%s)", title, author, isbn);
    }
}

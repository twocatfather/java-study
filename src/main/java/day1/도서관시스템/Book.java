package day1.도서관시스템;

import java.time.LocalDate;
import java.util.Objects;

// 불변객체로 구성
public final class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final LocalDate publishDate;
    private final int pageCount;

    public Book(String isbn, String title, String author, LocalDate publishDate, int pageCount) {
        // isbn, title, author, publishDate Objects.requireNonNull 사용해서 처리할것
        // pageCount 0보다 작거나 같을 때 에러를 터트릴것

        this.isbn = Objects.requireNonNull(isbn, "ISBN must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.author = Objects.requireNonNull(author, "author must not be null");
        this.publishDate = Objects.requireNonNull(publishDate, "publishDate must not be null");

        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be positive");
        }

        this.pageCount = pageCount;
    }

    // getters

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    // equals, hashcode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return pageCount == book.pageCount &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, publishDate, pageCount);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", pageCount=" + pageCount +
                '}';
    }
}

package day4.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BookStore {
    private List<Book> books;

    /**
     * 생성자 - 도서 목록 초기화
     */
    public BookStore() {
        this.books = new ArrayList<>();
    }


    /**
     * 도서 검색 메서드 (Predicate 활용)
     * Predicate 함수형 인터페이스를 활용하여 다양한 조건으로 도서 검색
     *
     * @param criteria 검색 조건
     * @return 조건에 맞는 도서 목록
     */
    public List<Book> findBooks(Predicate<Book> criteria) {
        return null;
    }

    /**
     * 도서 정보 변환 메서드 (Function 활용)
     * Function 함수형 인터페이스를 활용하여 도서 정보를 다른 형태로 변환
     *
     * @param transformer 변환 함수
     * @param <R> 변환 결과 타입
     * @return 변환된 결과 목록
     */
    public <R> List<R> transformBooks(Function<Book, R> transformer) {
        // 구현
        return null;
    }

    /**
     * 도서 정보 처리 메서드 (Consumer 활용)
     * Consumer 함수형 인터페이스를 활용하여 도서 정보 처리
     *
     * @param consumer 처리 함수
     */
    public void processBooks(Consumer<Book> consumer) {
        // 구현
    }

    /**
     * 특정 조건에 맞는 도서만 처리하는 메서드 (Predicate와 Consumer 조합)
     *
     * @param criteria 처리 대상 선택 조건
     * @param consumer 처리 함수
     */
    public void processFilteredBooks(Predicate<Book> criteria, Consumer<Book> consumer) {
        // 구현
    }

    /**
     * 새로운 도서 생성 메서드 (Supplier 활용)
     * Supplier 함수형 인터페이스를 활용하여 새 도서 생성 및 추가
     *
     * @param supplier 도서 생성 함수
     * @return 생성된 도서
     */
    public Book addBook(Supplier<Book> supplier) {
        return null;
    }

    /**
     * 여러 도서를 한번에 추가하는 메서드
     *
     * @param books 추가할 도서 목록
     */
    public void addBooks(List<Book> books) {
        this.books.addAll(books);
    }

    /**
     * 전체 도서 목록 반환
     *
     * @return 전체 도서 목록
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * 장르별 평균 평점 계산 메서드
     *
     * @return 장르별 평균 평점 Map
     */
    public Map<String, Double> getAverageRatingByGenre() {
        return null;
    }

    /**
     * 출판년도 기준 최신 도서 목록 반환
     *
     * @param limit 반환할 도서 수
     * @return 최신 도서 목록
     */
    public List<Book> getLatestBooks(int limit) {
        return null;
    }

    /**
     * 특정 저자의 모든 도서 목록 반환
     *
     * @param author 저자 이름
     * @return 해당 저자의 도서 목록
     */
    public List<Book> getBooksByAuthor(String author) {
        return null;
    }

    /**
     * 평점순으로 정렬된 도서 목록 반환
     *
     * @param ascending true면 오름차순, false면 내림차순
     * @return 정렬된 도서 목록
     */
    public List<Book> getBooksOrderedByRating(boolean ascending) {
        return null;
    }

    /**
     * 특정 제목의 도서를 찾는 메서드 (Optional 활용)
     *
     * @param title 찾을 도서 제목
     * @return Optional<Book> 객체
     */
    public Optional<Book> findBookByTitle(String title) {
        return null;
    }


    // 실행 예시
    public static void main(String[] args) {
        // 서점 인스턴스 생성
        BookStore store = new BookStore();

        // 샘플 도서 데이터 초기화
        initializeSampleBooks(store);

        System.out.println("===== 도서 검색 및 변환 예제 =====");

        // 1. Predicate를 사용한 도서 검색 예제
        System.out.println("\n1. Science Fiction 장르 도서 검색:");
        List<Book> sciFiBooks = store.findBooks(book -> "Science Fiction".equals(book.getGenre()));
        sciFiBooks.forEach(System.out::println);

        // 2. 여러 조건을 조합한 Predicate 예제
        System.out.println("\n2. 평점 4.0 이상의 2020년 이후 출판된 도서:");
        List<Book> highRatedRecentBooks = store.findBooks(
                book -> book.getRating() >= 4.0 && book.getYear() >= 2020);
        highRatedRecentBooks.forEach(System.out::println);

        // 3. Function을 사용한 도서 정보 변환 예제
        System.out.println("\n3. 도서 제목과 저자 정보만 추출:");
        List<String> bookTitleAndAuthors = store.transformBooks(
                book -> book.getTitle() + " by " + book.getAuthor());
        bookTitleAndAuthors.forEach(System.out::println);

        // 4. Function을 사용한 가격 정보 변환
        System.out.println("\n4. 도서별 가격/페이지 계산 (가성비):");
        List<Double> pricePerPage = store.transformBooks(book -> book.getPrice() / book.getPages());
        for (int i = 0; i < store.getAllBooks().size(); i++) {
            System.out.printf("%s: %.4f 원/페이지%n",
                    store.getAllBooks().get(i).getTitle(), pricePerPage.get(i));
        }

        // 5. Consumer를 사용한 도서 정보 처리 예제
        System.out.println("\n5. 모든 도서 10% 할인 적용:");
        store.processBooks(book -> book.setPrice(book.getPrice() * 0.9));
        store.getAllBooks().forEach(book ->
                System.out.println(book.getTitle() + ": " + book.getPrice() + "원"));

        // 6. Predicate와 Consumer 조합 예제
        System.out.println("\n6. 평점 3.5 미만 도서의 평점을 0.5 상향 조정:");
        store.processFilteredBooks(
                book -> book.getRating() < 3.5,
                book -> book.setRating(book.getRating() + 0.5)
        );
        store.getAllBooks().forEach(book ->
                System.out.println(book.getTitle() + ": 평점 " + book.getRating()));

        // 7. Supplier를 사용한 새 도서 추가 예제
        System.out.println("\n7. Supplier를 사용한 새 도서 추가:");
        Book newBook = store.addBook(() -> new Book(
                "The Future of AI", "Jane Smith", "Technology",
                2023, 4.7, 320, 28000));
        System.out.println("추가된 도서: " + newBook);

        // 8. 장르별 평균 평점 계산 예제
        System.out.println("\n8. 장르별 평균 평점:");
        Map<String, Double> averageRatingByGenre = store.getAverageRatingByGenre();
        averageRatingByGenre.forEach((genre, avgRating) ->
                System.out.printf("%s 장르 평균 평점: %.2f%n", genre, avgRating));

        // 9. 출판년도 기준 최신 도서 TOP 3
        System.out.println("\n9. 최신 출판 도서 TOP 3:");
        List<Book> latestBooks = store.getLatestBooks(3);
        latestBooks.forEach(System.out::println);

        // 10. Optional 활용 예제
        System.out.println("\n10. 특정 도서 검색 (Optional 활용):");
        Optional<Book> foundBook = store.findBookByTitle("Dune");
        foundBook.ifPresentOrElse(
                book -> System.out.println("찾은 도서: " + book),
                () -> System.out.println("해당 제목의 도서를 찾을 수 없습니다.")
        );
    }

    /**
     * 샘플 도서 데이터 초기화
     * @param store 도서를 추가할 BookStore 인스턴스
     */
    private static void initializeSampleBooks(BookStore store) {
        List<Book> sampleBooks = new ArrayList<>();

        sampleBooks.add(new Book("Dune", "Frank Herbert", "Science Fiction", 1965, 4.5, 412, 15000));
        sampleBooks.add(new Book("Neuromancer", "William Gibson", "Science Fiction", 1984, 4.3, 271, 14000));
        sampleBooks.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954, 4.8, 1178, 25000));
        sampleBooks.add(new Book("Pride and Prejudice", "Jane Austen", "Classic", 1813, 4.4, 279, 12000));
        sampleBooks.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, 4.6, 281, 13000));
        sampleBooks.add(new Book("Project Hail Mary", "Andy Weir", "Science Fiction", 2021, 4.7, 476, 18000));
        sampleBooks.add(new Book("Klara and the Sun", "Kazuo Ishiguro", "Literary Fiction", 2021, 3.9, 320, 17000));
        sampleBooks.add(new Book("The Algorithm Design Manual", "Steven S. Skiena", "Computer Science", 2008, 4.6, 730, 45000));
        sampleBooks.add(new Book("Clean Code", "Robert C. Martin", "Computer Science", 2008, 4.7, 464, 35000));
        sampleBooks.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, 3.3, 180, 11000));

        store.addBooks(sampleBooks);
    }
}

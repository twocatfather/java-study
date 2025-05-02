package day4.homework;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String title;
    private String author;
    private String genre;
    private int year;
    private double rating;
    private int pages;
    private double price;
}

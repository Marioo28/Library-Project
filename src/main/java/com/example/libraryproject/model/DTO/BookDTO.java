package com.example.libraryproject.model.DTO;

import com.example.libraryproject.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor
public class BookDTO {
    private String title;
    private int page_nr;
    private double price;
    private String description;
    private LocalDate year_of_release;
    private String publisher;
    private String author;

    public BookDTO(Book book) {

        this.title = book.getTitle();
        this.page_nr = book.getPage_nr();
        this.price = book.getPrice();
        this.description = book.getDescription();
        this.year_of_release = book.getYear_of_release();
        this.publisher = book.getPublisher().getName();
        this.author = book.getAuthor().getName();
    }
}

package com.example.libraryproject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String title;
    private int page_nr;
    private double price;
    private String description;
    private LocalDate year_of_release;
    private String publisher;
    private String author;
}

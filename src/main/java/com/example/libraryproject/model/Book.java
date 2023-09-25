package com.example.libraryproject.model;

import com.example.libraryproject.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String ISBN;
    private int page_nr;
    private double price;
    private String description;
    private LocalDate year_of_release;
    private Boolean isRented;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonBackReference
    public Publisher getPublisher() {
//        if (publisher==null){
//            return new Publisher("null", null);
//        }
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @JsonBackReference
    public Author getAuthor() {
//        if (publisher==null){
//            return new Author("null", null);
//        }
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}

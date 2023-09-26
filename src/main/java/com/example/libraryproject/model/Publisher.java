package com.example.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    List<Book> books = new ArrayList<>();

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    @JsonManagedReference
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Book> bookList = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }


    public Author(String name, List<Book> bookList) {
        this.name = name;
        this.bookList = bookList;
    }

    @JsonManagedReference
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
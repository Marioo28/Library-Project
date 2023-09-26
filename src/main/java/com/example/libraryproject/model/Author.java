package com.example.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
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
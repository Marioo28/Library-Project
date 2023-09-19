package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByTitle(String title);
    Book findByTitle(String string);

    List<Book> findAll();
}

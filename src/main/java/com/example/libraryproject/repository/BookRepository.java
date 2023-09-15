package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBookByTitle(String title);

    Optional<Book> findByTitle(String title);
}

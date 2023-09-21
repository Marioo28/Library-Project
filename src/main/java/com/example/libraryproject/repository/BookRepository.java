package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByTitle(String title);
    Book findByTitle(String string);


    List<Book> findAll();
//    List<BookDTO> findAllDTO();

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%" + " OR b.ISBN LIKE %?1%"
            + " OR b.author.name LIKE %?1%" + " OR b.publisher.name LIKE %?1%")
    List<BookDTO> search(String keyword);
}

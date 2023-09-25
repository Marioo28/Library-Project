package com.example.libraryproject.repository;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.DTO.AuthorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);
    @Query(value = "SELECT a.name, count(b.id) FROM Author a LEFT JOIN a.bookList b GROUP BY a.name")
    List<Object[]> findAuthorBookCount();
    Author save(AuthorDTO authorDTO);

}

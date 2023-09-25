package com.example.libraryproject.repository;

import com.example.libraryproject.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByName(String name);
    @Query(value = "SELECT a.name, count(b.id) FROM Publisher a LEFT JOIN a.books b GROUP BY a.name")
    List<Object[]> findPublisherBookCount();
}

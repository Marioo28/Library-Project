package com.example.libraryproject.repository;

import com.example.libraryproject.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByName(String name);
}
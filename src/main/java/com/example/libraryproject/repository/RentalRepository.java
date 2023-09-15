package com.example.libraryproject.repository;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Rental;
import com.example.libraryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByUser(User user);

}

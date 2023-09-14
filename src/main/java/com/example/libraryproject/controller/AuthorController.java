package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/saveAuthor")
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping("/find/{id}")
    public Author findAuthorById(@PathVariable int id) {
        return authorService.findAuthorById(id);
    }

    @GetMapping("/getAllAuthors")
    public List<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        authorService.removeAuthorById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/addBook")
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @GetMapping("/find/{id}")
    public Book findBookById(@PathVariable int id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/getAllBooks")
    public List<Book> findAllBooksSortedByPrice() {
        return bookService.getBooksSortedByPrice();
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeById(@PathVariable int id) {
        bookService.removeBookById(id);
        return ResponseEntity.noContent().build();
    }
}
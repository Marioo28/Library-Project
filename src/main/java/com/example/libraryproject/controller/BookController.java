package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.model.DTO.PublisherDTO;
import com.example.libraryproject.service.BookService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookService bookService;


    @PostMapping("/addBook")
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

//    @GetMapping("/find/{id}")
//    public ResponseEntity<BookDTO> findBookById(@PathVariable int id){
//        Book book = bookService.findBookById(id);
//        BookDTO bookDTO = modelMapper.map(book, BookDTO.class );
//        return ResponseEntity.ok().body(bookDTO);
//    }

    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks(){
        return bookService.findAllBooks().stream().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeById(@PathVariable int id) {
        bookService.removeBookById(id);
        return ResponseEntity.noContent().build();
    }
}
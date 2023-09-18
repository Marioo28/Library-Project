package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.service.BookService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping()
    public String showBooks(final ModelMap modelMap) {
        List<BookDTO> books = bookService.findAllBooks();
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        Book savedBook = bookService.saveBook(book);


        return "homePage";
    }


    @PostMapping("/addBook")
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable int id) {
        BookDTO bookDTO = bookService.findBookById(id);
//      Book book = modelMapper.map(book, BookDTO.class );
        return ResponseEntity.ok().body(bookDTO);
    }

    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks() {
        return bookService.findAllBooks().stream().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeById(@PathVariable int id) {
        bookService.removeBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<Book>> getBookByName(@RequestParam String name) {
        return new ResponseEntity<List<Book>>(bookService.findBookByTitle(name), HttpStatus.OK);
    }
}
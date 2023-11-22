package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.service.AuthorService;
import com.example.libraryproject.service.BookService;
import com.example.libraryproject.service.PublisherService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookController {

    @Autowired
    private ModelMapper modelMapper;

    private final BookService bookService;

    private final AuthorService authorService;

    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping()
    public String showBooks(final ModelMap modelMap) {
        List<BookDTO> books = bookService.findAllBooks();
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("book", new BookDTO());
        modelMap.addAttribute("bookDTO", new BookDTO());

        return "books";
    }

    @GetMapping("/addBook")
    public String showCreateForm(BookDTO bookDTO, Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createAuthor(BookDTO bookDTO, BindingResult bindingResult, Model model, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "add-book";
        }

        bookService.addBook(bookDTO);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/api/books";
    }

    //method in header
    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<BookDTO> books = bookService.searchBooks(keyword);

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("bookId") int id, Model model) {
        final Book book = bookService.findBookById(id);

        model.addAttribute("books1", book);
        return "update-book";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Book theBook) {
        // save the book
        bookService.saveBook(theBook);
        // use a redirect to prevent duplicate submissions
        return "redirect:/api/books";
    }

    @RequestMapping("/deleteBook/{id}")
    public String removeById(@PathVariable("id") int id, Model model) {
        bookService.removeBookById(id);
//        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/api/books";
    }
}
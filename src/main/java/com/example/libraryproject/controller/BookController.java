package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.service.BookService;
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
        modelMap.addAttribute("book", new BookDTO());
        modelMap.addAttribute("bookDTO", new BookDTO());

        return "books";
    }

    @PostMapping
    public String addBook(@ModelAttribute BookDTO book) {
        BookDTO savedBook = bookService.addBook(book);

        return "homePage";
    }

    @GetMapping("/addBook")
    public String showCreateForm(BookDTO bookDTO) {
        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createAuthor(BookDTO bookDTO, BindingResult bindingResult, Model model, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "add-book";
        }

        bookService.addBook(bookDTO);
//        modelMap.addAttribute("book", new BookDTO());
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/api/books";
    }

    @GetMapping("/search")
    public String getBookByTitle(@RequestParam("searchTerm") String searchTerm, final Model model) {
        BookDTO bookDTO = bookService.findBookByTitleDTO(searchTerm);
        model.addAttribute("bookDTO", bookDTO);
        return "searchResults";

    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<BookDTO> books = bookService.searchBooks(keyword);

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @RequestMapping("/deleteBook/{id}")
    public String removeById(@PathVariable("id") int id, Model model) {
        bookService.removeBookById(id);
        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }


//    @PostMapping("/addBook")
//    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
//        return bookService.addBook(bookDTO);
//    }
//
//    @GetMapping("/find/{id}")
//    public ResponseEntity<BookDTO> findBookById(@PathVariable int id) {
//        BookDTO bookDTO = bookService.findBookById(id);
////      Book book = modelMapper.map(book, BookDTO.class );
//        return ResponseEntity.ok().body(bookDTO);
//    }

//    @GetMapping("/getAllBooks")
//    public List<BookDTO> getAllBooks() {
//        return bookService.findAllBooks().stream().map(book -> modelMapper.map(book, BookDTO.class))
//                .collect(Collectors.toList());
//    }



//    @Transactional
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> removeById(@PathVariable int id) {
//        bookService.removeBookById(id);
//        return ResponseEntity.noContent().build();
//    }

//    @GetMapping("/findByTitle")
//    public ResponseEntity<List<Book>> getBookByName(@RequestParam String name) {
//        return new ResponseEntity<List<Book>>(bookService.findBookByTitle(name), HttpStatus.OK);
//    }
}
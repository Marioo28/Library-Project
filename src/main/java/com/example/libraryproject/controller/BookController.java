package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.service.AuthorService;
import com.example.libraryproject.service.BookService;
import com.example.libraryproject.service.PublisherService;
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

    private final BookService bookService;

    private final AuthorService authorService;

    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService,PublisherService publisherService) {
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

    @PostMapping
    public String addBook(@ModelAttribute BookDTO book) {
        BookDTO savedBook = bookService.addBook(book);

        return "homePage";
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
//        modelMap.addAttribute("book", new BookDTO());
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/api/books";
    }

    //method down in book.html
    @GetMapping("/search")
    public String getBookByTitle(@RequestParam("searchTerm") String searchTerm, final Model model) {
        BookDTO bookDTO = bookService.findBookByTitleDTO(searchTerm);
        model.addAttribute("bookDTO", bookDTO);
        return "searchResults";

    }

    //method in header
    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<BookDTO> books = bookService.searchBooks(keyword);

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "update-book-test";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book updatedBook) {
        // Here, you can update the author's information using the updatedAuthor object
        // Save it to the database using the authorRepository or your data access layer
        bookService.saveBook(updatedBook);

        // Redirect to the author list or a success page
        return "redirect:/api/books";
    }



    @RequestMapping("/deleteBook/{id}")
    public String removeById(@PathVariable("id") int id, Model model) {
        bookService.removeBookById(id);
//        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", bookService.findAllBooks());
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
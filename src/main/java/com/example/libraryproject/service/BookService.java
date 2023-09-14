package com.example.libraryproject.service;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBookById(int id) {
        bookRepository.deleteById(id);
    }

    public Book findBookById(int id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> getBooksSortedByPrice() {
        return bookRepository.findAll().stream().sorted(Comparator.comparing(Book::getPrice).reversed()).toList();
    }


    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();

        // Convert Book entities to BookDTOs
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book);
                    bookDTO.setPublisher(book.getPublisher().getName());
                    bookDTO.setAuthor(book.getAuthor().getName());
                    return bookDTO;
                })
                .collect(Collectors.toList());

        return bookDTOs;
    }


    public BookDTO addBook(BookDTO bookDTO) {
        Author author = authorService.findOrCreateAuthor(bookDTO.getAuthor());
        Publisher publisher = publisherService.findOrCreatePublisher(bookDTO.getPublisher());

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setPage_nr(bookDTO.getPage_nr());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        book.setYear_of_release(bookDTO.getYear_of_release());
        book.setPublisher(publisher);
        book.setAuthor(author);

        try {
            bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookDTO;
    }
}

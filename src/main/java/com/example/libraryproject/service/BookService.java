package com.example.libraryproject.service;

import com.example.libraryproject.exception.NotFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;


    public void removeBookById(int id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getId());
    }

    public Book findById(int id) {
        return bookRepository.findById(id).get();
    }

    //    -----------Book Update 25/09------------
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public Book findBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }


//    -----------Book Update 25/09------------

    public List<BookDTO> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return findAllBooks();
    }

//    public BookDTO findOrCreateBook(String title) {
//        return bookRepository.findByTitleBook(title).orElseGet(() -> addBook(new BookDTO()));
//    }


//    public BookDTO findBookById(int id) {
//        Book book = bookRepository.findById(id).get();
//
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setId(bookDTO.getId());
//        bookDTO.setTitle(book.getTitle());
//        bookDTO.setISBN(book.getISBN());
//        bookDTO.setPage_nr(book.getPage_nr());
//        bookDTO.setPrice(book.getPrice());
//        bookDTO.setDescription(book.getDescription());
//        bookDTO.setYear_of_release(book.getYear_of_release());
//        bookDTO.setIsRented(book.getIsRented());
//        bookDTO.setPublisher(book.getPublisher().getName());
//        bookDTO.setAuthor(book.getAuthor().getName());
//
//        return bookDTO;
//    }


//    public List<Book> getBooksSortedByPrice() {
//        return bookRepository.findAll().stream().sorted(Comparator.comparing(Book::getPrice).reversed()).toList();
//    }


    public BookDTO findBookByTitleDTO(String name) {
        Book book = bookRepository.findByTitle(name);
        if (book == null) {
            throw new NotFoundException("No such book");
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setISBN(book.getISBN());
        bookDTO.setPage_nr(book.getPage_nr());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setYear_of_release(book.getYear_of_release());
        bookDTO.setIsRented(book.getIsRented());
        bookDTO.setPublisher(book.getPublisher().getName());
        bookDTO.setAuthor(book.getAuthor().getName());

        return bookDTO;
    }


    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();

        // Convert Book entities to BookDTOs
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO(book);
                    bookDTO.setId(book.getId());
                    bookDTO.setTitle(book.getTitle());
                    bookDTO.setISBN(book.getISBN());
                    bookDTO.setPage_nr(book.getPage_nr());
                    bookDTO.setPrice(book.getPrice());
                    bookDTO.setDescription(book.getDescription());
                    bookDTO.setYear_of_release(book.getYear_of_release());
                    bookDTO.setIsRented(book.getIsRented());
                    bookDTO.setPublisher(book.getPublisher().getName());
                    bookDTO.setAuthor(book.getAuthor().getName());
                    return bookDTO;
                })
                .collect(Collectors.toList());

        return bookDTOs;
    }

    //    public List<BookDTO> findAllBooksDTO() {
//        List<BookDTO> booksDTO = bookRepository.findAllDTO();
//
//        // Convert Book entities to BookDTOs
//        List<Book> bookDTOs = books.stream()
//                .map(book -> {
//                    BookDTO bookDTO = new BookDTO(book);
//                    bookDTO.setId(book.getId());
//                    bookDTO.setTitle(book.getTitle());
//                    bookDTO.setISBN(book.getISBN());
//                    bookDTO.setPage_nr(book.getPage_nr());
//                    bookDTO.setPrice(book.getPrice());
//                    bookDTO.setDescription(book.getDescription());
//                    bookDTO.setYear_of_release(book.getYear_of_release());
//                    bookDTO.setIsRented(book.getIsRented());
//                    bookDTO.setPublisher(book.getPublisher().getName());
//                    bookDTO.setAuthor(book.getAuthor().getName());
//                    return bookDTO;
//                })
//                .collect(Collectors.toList());
//
//        return bookDTOs;
//    }
    public Book saveBook(Book bookToSave) {
        Author author = authorService.findOrCreateAuthor(bookToSave.getAuthor().getName());
        Publisher publisher = publisherService.findOrCreatePublisher(bookToSave.getPublisher().getName());

        Book book = new Book();
        book.setTitle(bookToSave.getTitle());
        book.setISBN(bookToSave.getISBN());
        book.setPage_nr(bookToSave.getPage_nr());
        book.setPrice(bookToSave.getPrice());
        book.setDescription(bookToSave.getDescription());
        book.setYear_of_release(bookToSave.getYear_of_release());
        book.setIsRented(bookToSave.getIsRented());
        book.setPublisher(publisher);
        book.setAuthor(author);

        try {
            bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;

    }

    public BookDTO addBook(BookDTO bookDTO) {
        Author author = authorService.findOrCreateAuthor(bookDTO.getAuthor());
        Publisher publisher = publisherService.findOrCreatePublisher(bookDTO.getPublisher());

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setISBN(bookDTO.getISBN());
        book.setPage_nr(bookDTO.getPage_nr());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        book.setYear_of_release(bookDTO.getYear_of_release());
        book.setIsRented(bookDTO.getIsRented());
        book.setPublisher(publisher);
        book.setAuthor(author);

        try {
            bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookDTO;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}

package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

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
}

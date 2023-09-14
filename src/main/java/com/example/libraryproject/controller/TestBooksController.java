package com.example.libraryproject.controller;

import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api/carti")
public class TestBooksController {

//    @Autowired
//    private ModelMapper modelMapper;
//
//    private BookService bookService;
//
//    public TestBooksController(BookService bookService) {
//        this.bookService = bookService;
//    }
//
////    @GetMapping
////    public List<BookDTO> getAllBooks(){
////        return bookService.findAllBooks().stream().map(book -> modelMapper.map(book, BookDTO.class))
////                .collect(Collectors.toList());
////    }

}

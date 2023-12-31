package com.example.libraryproject.service;


import com.example.libraryproject.exception.NotFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.AuthorDTO;
import com.example.libraryproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return findOrCreateAuthor(author.getName());
    }

    public Author saveAuthor2(Author authorToSave) {
        Author author = authorRepository.findById(authorToSave.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", authorToSave.getId())));
        author.setName(authorToSave.getName());
        author.setBookList(authorToSave.getBookList());

        try {
            authorRepository.save(authorToSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public Author updateAuthor(Author authorToUpdate) {

        Author author = new Author();
        author.setId(authorToUpdate.getId());
        author.setName(authorToUpdate.getName());
        author.setBookList(authorToUpdate.getBookList());

        return authorRepository.save(author);
    }
//    public AuthorDTO updateAuthorDTO(Author authorToUpdate) {
//
//        AuthorDTO author = new AuthorDTO();
//        author.setId(authorToUpdate.getId());
//        author.setName(authorToUpdate.getName());
//        author.setBookList(authorToUpdate.getBookList().stream()
//                .map(Book::getTitle).collect(Collectors.toList()));
//
//        return updateAuthor(author);
//    }

    public Author save(AuthorDTO authorDTO) {
        return authorRepository.save(authorDTO);
    }

    public void removeAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    public Author findById(int id) {
        Optional<Author> result = authorRepository.findById(id);
        Author theAuthor = null;
        if (result.isPresent()) {
            theAuthor = result.get();
        } else {
            throw new RuntimeException("Did not find author id - " + id);
        }
        return theAuthor;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findOrCreateAuthor(String name) {
        return authorRepository.findByName(name).orElseGet(() -> authorRepository.save(new Author(name)));
    }

//    public Map<String, Integer> getAuthorBookCount() {
//        List<Object[]> results = authorRepository.findAuthorBookCount();
//        Map<String, Integer> authorBookCount = new HashMap<>();
//
//        for (Object[] result : results) {
//            String authorName = (String) result[0];
//            Integer bookCount = ((Number) result[1]).intValue();
//            authorBookCount.put(authorName, bookCount);
//        }
//        return authorBookCount;
//    }


    public List<AuthorDTO> findAllAuthorsDTO() {
        List<Author> authors = authorRepository.findAll();

        List<AuthorDTO> authorDTOList = authors.stream()
                .map(author -> {
                    AuthorDTO authorDTO = new AuthorDTO();
                    authorDTO.setId(author.getId());
                    authorDTO.setName(author.getName());
                    List<String> bookNames = author.getBookList()
                            .stream().map(Book::getTitle)
                            .collect(Collectors.toList());
                    authorDTO.setBookList(bookNames);
                    return authorDTO;
                }).collect(Collectors.toList());

        return authorDTOList;
    }

}

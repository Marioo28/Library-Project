package com.example.libraryproject.service;


import com.example.libraryproject.model.Author;
import com.example.libraryproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void removeAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    public Author findAuthorById(int id) {
        return authorRepository.findById(id).get();
    }

    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }

    public Author findOrCreateAuthor(String name){
        return authorRepository.findByName(name).orElseGet(() -> authorRepository.save(new Author(name)));
    }

    public Map<String, Integer> getAuthorBookCount() {
        List<Object[]> results = authorRepository.findAuthorBookCount();
        Map<String, Integer> authorBookCount = new HashMap<>();

        for (Object[] result : results) {
            String authorName = (String) result[0];
            Integer bookCount = ((Number) result[1]).intValue();
            authorBookCount.put(authorName, bookCount);
        }
        return authorBookCount;
    }

}

package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.DTO.AuthorDTO;
import com.example.libraryproject.service.AuthorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
//@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public String showAuthors() {
        return "authors";
    }


    @GetMapping("/create")
    public String showAuthorDTO(final ModelMap modelMap) {

        List<AuthorDTO> authorsDTO = authorService.findAllAuthors().stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
        modelMap.addAttribute("authorDTO", new AuthorDTO());
        modelMap.addAttribute("elements", authorsDTO);
        return "welcome";
    }

    @PostMapping("/create")
    public String handleNewAuthor(@Valid @ModelAttribute("authorDTO") final AuthorDTO authorDTO) {
        return "redirect:/confirmation";
    }


    @PostMapping("/saveAuthor")
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable int id) {
        Author author = authorService.findAuthorById(id);
        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        return ResponseEntity.ok().body(authorDTO);
    }

    @GetMapping("/getAllAuthors")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.findAllAuthors().stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        authorService.removeAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAuthorBook")
    public Map<String, Integer> getAuthorBookCount() {
        return authorService.getAuthorBookCount();
    }

}

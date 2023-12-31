package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.DTO.AuthorDTO;
import com.example.libraryproject.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public String showAuthors(final ModelMap modelMap) {
        List<AuthorDTO> authors = authorService.findAllAuthorsDTO();
        modelMap.addAttribute("authors", authors);
        modelMap.addAttribute("author", new AuthorDTO());
        return "authors";
    }

    @GetMapping("/addAuthor")
    public String showCreateForm(Author author) {
        return "add-author";
    }

    @RequestMapping("/add-author")
    public String createAuthor(Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-author";
        }

        authorService.saveAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/api/authors";
    }


    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("authorId") int id, Model model) {
        final Author author = authorService.findById(id);

        model.addAttribute("author1", author);
        return "update-author";
    }
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("authors") Author theAuthor) {
        // save the book
        authorService.saveAuthor2(theAuthor);
        // use a redirect to prevent duplicate submissions
        return "redirect:/api/authors";
    }
//    @GetMapping("/updateAuthor/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        Author author = authorService.findById(id);
//
//        model.addAttribute("authorForUpdate", author);
//
//        return "update-author";
//    }
//
//    @PostMapping("/update-author/{id}")
//    public String updateAuthor(@PathVariable("id") int id, Author author, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            author.setId(id);
//            return "update-author";
//        }
//
//        authorService.saveAuthor(author);
//        model.addAttribute("author", authorService.findAllAuthors());
//        return "redirect:api/authors";
//    }
//
//    @GetMapping("/editAuthor/{id}")
//    public String editAuthor(@PathVariable int id, Model model) {
//        Author author = authorService.findById(id);
//         model.addAttribute("author", author);
//        return "update-author-test";
//    }
//
//    @PostMapping("/updateAuthor2")
//    public String updateAuthor(@ModelAttribute Author updatedAuthor) {
//        // Here, you can update the author's information using the updatedAuthor object
//        // Save it to the database using the authorRepository or your data access layer
//        authorService.updateAuthor(updatedAuthor);
//
//        // Redirect to the author list or a success page
//        return "redirect:/api/authors";
//    }

    @RequestMapping("/deleteAuthor/{id}")
    public String removeById(@PathVariable("id") int id, Model model) {
        authorService.removeAuthorById(id);
        List<AuthorDTO> authors = authorService.findAllAuthorsDTO();
        model.addAttribute("authors", authors);
        return "authors";
    }

//    @GetMapping("/create")
//    public String showAuthorDTO(final ModelMap modelMap) {
//
//        List<AuthorDTO> authorsDTO = authorService.findAllAuthors().stream()
//                .map(author -> modelMapper.map(author, AuthorDTO.class))
//                .collect(Collectors.toList());
//        modelMap.addAttribute("authorDTO", new AuthorDTO());
//        modelMap.addAttribute("elements", authorsDTO);
//        return "welcome";
//    }

//    @PostMapping("/create")
//    public String handleNewAuthor(@Valid @ModelAttribute("authorDTO") final AuthorDTO authorDTO) {
//        return "redirect:/confirmation";
//    }


//    @PostMapping("/saveAuthor")
//    public Author saveAuthor(@RequestBody Author author) {
//        return authorService.saveAuthor(author);
//    }

//    @GetMapping("/find/{id}")
//    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable int id) {
//        Author author = authorService.findAuthorById(id);
//        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
//        return ResponseEntity.ok().body(authorDTO);
//    }

//    @GetMapping("/getAllAuthors")
//    public List<AuthorDTO> getAllAuthors() {
//        return authorService.findAllAuthors().stream()
//                .map(author -> modelMapper.map(author, AuthorDTO.class))
//                .collect(Collectors.toList());
//    }


//    @DeleteMapping("/delete-Author/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable int id) {
//        authorService.removeAuthorById(id);
//        return ResponseEntity.noContent().build();
//    }

//    @GetMapping("/getAuthorBook")
//    public Map<String, Integer> getAuthorBookCount() {
//        return authorService.getAuthorBookCount();
//    }

}

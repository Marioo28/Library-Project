package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.DTO.AuthorDTO;
import com.example.libraryproject.model.DTO.PublisherDTO;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.service.PublisherService;
import jakarta.transaction.Transactional;
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
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public String showPublishers(final ModelMap modelMap) {
        List<Publisher> publishers = publisherService.findAllPublishers();
        modelMap.addAttribute("publishers", publishers);
        return "publishers";
    }

    @PostMapping("/savePublisher")
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PublisherDTO> findPublisherById(@PathVariable int id) {
        Publisher publisher = publisherService.findPublisherById(id);
        PublisherDTO publisherDTO = modelMapper.map(publisher, PublisherDTO.class);
        return ResponseEntity.ok().body(publisherDTO);
    }

    @GetMapping("/getAllPublishers")
    public List<PublisherDTO> findAllPublishers() {
        return publisherService.findAllPublishers().stream()
                .map(publisher -> modelMapper.map(publisher, PublisherDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeById(@PathVariable int id) {
        publisherService.removePublisherById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getPublisherBook")
    public Map<String, Integer> getAuthorBookCount() {
        return publisherService.getPublisherBookCount();
    }
}

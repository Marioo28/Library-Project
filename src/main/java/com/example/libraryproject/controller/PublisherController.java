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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/savePublisher")
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PublisherDTO> findPublisherById(@PathVariable int id){
        Publisher publisher = publisherService.findPublisherById(id);
        PublisherDTO publisherDTO = modelMapper.map(publisher, PublisherDTO.class);
        return ResponseEntity.ok().body(publisherDTO);
    }

    @GetMapping("/getAllPublishers")
    public List<Publisher> findAllPublishers() {
        return publisherService.findAllPublishers();
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeById(@PathVariable int id) {
        publisherService.removePublisherById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.libraryproject.controller;

import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.service.PublisherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @PostMapping("/savePublisher")
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @GetMapping("/find/{id}")
    public Publisher findPublisherById(@PathVariable int id) {
        return publisherService.findPublisherById(id);
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

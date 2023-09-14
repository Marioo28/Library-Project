package com.example.libraryproject.service;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher savePublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }
    public void removePublisherById(int id) {
        publisherRepository.deleteById(id);
    }

    public Publisher findPublisherById(int id) {
        return publisherRepository.findById(id).get();
    }

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher findOrCreatePublisher(String name){
        return publisherRepository.findByName(name).orElseGet(() -> publisherRepository.save(new Publisher(name)));
    }

}

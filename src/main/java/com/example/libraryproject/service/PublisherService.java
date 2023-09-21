package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.PublisherDTO;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher savePublisher(Publisher publisher) {
        return findOrCreatePublisher(publisher.getName());
    }

    public void removePublisherById(int id) {
        publisherRepository.deleteById(id);
    }

    public Publisher findPublisherById(int id) {
        return publisherRepository.findById(id).get();
    }

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher findOrCreatePublisher(String name) {
        return publisherRepository.findByName(name).orElseGet(() -> publisherRepository.save(new Publisher(name)));
    }

    public Map<String, Integer> getPublisherBookCount() {
        List<Object[]> results = publisherRepository.findPublisherBookCount();
        Map<String, Integer> publisherBookCount = new HashMap<>();

        for (Object[] result : results) {
            String authorName = (String) result[0];
            Integer bookCount = ((Number) result[1]).intValue();
            publisherBookCount.put(authorName, bookCount);
        }
        return publisherBookCount;
    }

    public List<PublisherDTO> findAllPublishersDTO() {
        List<Publisher> publishers = publisherRepository.findAll();

        List<PublisherDTO> publisherDTOList = publishers.stream()
                .map(publisher -> {
                    PublisherDTO publisherDTO = new PublisherDTO();
                    publisherDTO.setId(publisher.getId());
                    publisherDTO.setName(publisher.getName());
                    List<String> bookNames = publisher.getBooks()
                            .stream().map(Book::getTitle)
                            .collect(Collectors.toList());
                    publisherDTO.setBookList(bookNames);
                    return publisherDTO;
                }).collect(Collectors.toList());

        return publisherDTOList;
    }

}

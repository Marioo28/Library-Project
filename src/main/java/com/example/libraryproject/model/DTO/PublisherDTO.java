package com.example.libraryproject.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PublisherDTO {
    private String name;
    private List<String> bookList;
}

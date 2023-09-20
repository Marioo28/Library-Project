package com.example.libraryproject.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PublisherDTO {
    private int id;
    private String name;
    private List<String> bookList;
}

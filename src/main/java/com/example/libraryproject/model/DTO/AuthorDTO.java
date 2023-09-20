package com.example.libraryproject.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private int id;
    @NotNull(message = "no null fields")
    private String name;
    private List<String> bookList;
}

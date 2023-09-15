package com.example.libraryproject.model.DTO;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    private LocalDate rental_date;
    private LocalDate return_date;
    private Book book;
    private User user;
}

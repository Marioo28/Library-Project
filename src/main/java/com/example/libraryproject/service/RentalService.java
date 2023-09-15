package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.DTO.RentalDTO;
import com.example.libraryproject.model.Rental;
import com.example.libraryproject.model.User;
import com.example.libraryproject.repository.RentalRepository;
import com.example.libraryproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    public List<RentalDTO> findAll() {
        return rentalRepository.findAll()
                .stream()
                .map(rental -> modelMapper.map(rental, RentalDTO.class))
                .collect(Collectors.toList());
    }

    public RentalDTO addRent(RentalDTO rentalDTO) {

        User user = userService.findOrCreateUser(rentalDTO.getUser().getUsername());
        Book book = bookService.findByTitle(rentalDTO.getBook().getTitle());

        Rental rental = new Rental();
        rental.setRental_date(rentalDTO.getRental_date());
        rental.setReturn_date(rentalDTO.getReturn_date());
        rental.setBook(rentalDTO.getBook());
        rental.setUser(rentalDTO.getUser());
        try {
            rentalRepository.save(rental);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentalDTO;
    }

    public void removeRentById(int id) {
        rentalRepository.deleteById(id);
    }
}

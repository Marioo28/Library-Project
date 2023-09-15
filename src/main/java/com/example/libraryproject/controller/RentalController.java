package com.example.libraryproject.controller;

import com.example.libraryproject.model.DTO.BookDTO;
import com.example.libraryproject.model.DTO.RentalDTO;
import com.example.libraryproject.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addRent")
    public RentalDTO saveRent(@RequestBody RentalDTO rentalDTO) {
        return rentalService.addRent(rentalDTO);
    }

    @GetMapping("/getAllRents")
    public List<RentalDTO> getAllRents() {
        return rentalService.findAll().stream().map(rent -> modelMapper.map(rent, RentalDTO.class))
                .collect(Collectors.toList());
    }
}

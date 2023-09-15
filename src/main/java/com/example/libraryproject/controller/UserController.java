package com.example.libraryproject.controller;

import com.example.libraryproject.model.DTO.UserDTO;
import com.example.libraryproject.model.User;
import com.example.libraryproject.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/saveUser")
    public User saveAuthor(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        userService.removeUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}

package com.example.libraryproject.service;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.User;
import com.example.libraryproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User findOrCreateUser(String username){
        return userRepository.findByUsername(username).orElseGet(() -> userRepository.save(new User(username)));
    }

    public User saveUser(User user){return userRepository.save(user);}

    public void removeUserById(int id){
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}

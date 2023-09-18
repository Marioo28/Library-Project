package com.example.libraryproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.SplittableRandom;

@Controller
public class ConfirmationController {
    @GetMapping("/confirmation")
    public String getConfirmation(){
        return "confirmation";
    }
}

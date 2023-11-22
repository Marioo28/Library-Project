package com.example.libraryproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping
    public String showHomePage() {
        return "homePage";
    }

    @PostMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
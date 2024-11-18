package com.example.library_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {

    @GetMapping("/view-books")
    public String viewBooks() {
        return "books";  // This will load the books.html from the static directory
    }
}

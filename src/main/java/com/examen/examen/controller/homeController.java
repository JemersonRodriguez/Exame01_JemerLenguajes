package com.examen.examen.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class homeController {
    @GetMapping("/")
    public String index() {
        return "index"; // Thymeleaf buscará index.html en templates
    }
}

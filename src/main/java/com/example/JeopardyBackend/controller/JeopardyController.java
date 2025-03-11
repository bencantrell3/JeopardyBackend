package com.example.JeopardyBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JeopardyController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}

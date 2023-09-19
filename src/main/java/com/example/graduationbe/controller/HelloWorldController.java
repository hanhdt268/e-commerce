package com.example.graduationbe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")

public class HelloWorldController {

    @GetMapping("/hello")
    public String testApi(){
        return "Hello world";
    }
}

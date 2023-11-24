package com.example.graduationbe.controller;


import com.example.graduationbe.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sendMail")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class SendMailController {
    private final EmailService emailService;

    @GetMapping("/")
    public String sendMail() {
        return this.emailService.sendSimpleEmail();
    }
}

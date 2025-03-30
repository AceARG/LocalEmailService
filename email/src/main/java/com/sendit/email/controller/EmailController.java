package com.sendit.email.controller;

import com.sendit.email.model.EmailDetails;
import com.sendit.email.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/send")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleEmail(details);
    }

    // Sending email with attachment
    @PostMapping("/sendWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendEmailWithAttachment(details);
    }

}

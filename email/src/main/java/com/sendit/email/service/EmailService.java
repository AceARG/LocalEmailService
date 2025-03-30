package com.sendit.email.service;

import com.sendit.email.model.EmailDetails;

public interface EmailService {
    // Method to send simple email
    String sendSimpleEmail(EmailDetails details);

    // Method to send email with attachment
    String sendEmailWithAttachment(EmailDetails details);
}

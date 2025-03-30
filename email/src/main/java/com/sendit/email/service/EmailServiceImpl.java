package com.sendit.email.service;

import com.sendit.email.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Method to send a simple email
    @Override
    public String sendSimpleEmail(EmailDetails details) {
        try {
            if (details.isHtml()) {
                return sendHtmlEmail(details);
            }

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMessageBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail: " + e.getMessage();

        }
    }

    // Method to send email with attachment
    @Override
    public String sendEmailWithAttachment(EmailDetails details) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            // Setting up mail details
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.isHtml() ? details.getMessageBody() : details.getMessageBody(), details.isHtml());
            mimeMessageHelper.setSubject(details.getSubject());

            // Adding the attachment
            if (details.getAttachment() != null && !details.getAttachment().isEmpty()) {
                FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
                mimeMessageHelper.addAttachment(file.getFilename(), file);
            }

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        } catch (MessagingException e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }

    private String sendHtmlEmail(EmailDetails details) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMessageBody(), true); // Set to true for HTML

            javaMailSender.send(mimeMessage);
            return "HTML Mail Sent Successfully...";
        } catch (MessagingException e) {
            return "Error while Sending HTML Mail: " + e.getMessage();
        }
    }
}

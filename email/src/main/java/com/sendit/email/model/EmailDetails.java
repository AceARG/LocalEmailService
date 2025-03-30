package com.sendit.email.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    // Recipient's email address
    private String recipient;

    // Subject of the mail
    private String subject;

    // Message body
    private String messageBody;

    // Path to attachment
    private String attachment;

    // Whether the email should be HTML format
    private boolean isHtml = false;
}

package com.portfolio.backenduspiri.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Email {
    
    private String to;
    private String subject;
    private String message;

    public Email() {
    }

    public Email(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }
    
}

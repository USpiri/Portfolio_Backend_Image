package com.portfolio.backenduspiri.security.util;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Message {
    
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }
    
}

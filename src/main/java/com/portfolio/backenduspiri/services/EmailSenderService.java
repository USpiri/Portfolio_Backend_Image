package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.service_interface.IEmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements IEmailSender{
    
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;
    
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailFrom);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        
        this.mailSender.send(mail);
    }
    
}

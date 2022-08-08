package com.portfolio.backenduspiri.service_interface;

public interface IEmailSender {
    void sendEmail( String to, String subject, String message );
}

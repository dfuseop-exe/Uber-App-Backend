package com.project.uber.uberApp.services;

public interface EmailService {
    public void sendEmail(String email, String subject, String body);
    public void sendEmail(String email[], String subject, String body);
}

package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImplementation implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String email, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            log.info("Email Sent Successfully");
        }catch (Exception e){
            log.info("Can not send email {}", e.getMessage());
        }
    }

    @Override
    public void sendEmail(String[] email, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            log.info("Email Sent Successfully");
        }catch (Exception e){
            log.info("Can not send Email");
        }
    }
}

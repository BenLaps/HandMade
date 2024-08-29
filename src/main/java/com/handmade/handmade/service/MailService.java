package com.handmade.handmade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    private final Environment env;
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(Environment env, JavaMailSender javaMailSender) {
        this.env = env;
        this.javaMailSender = javaMailSender;
    }
    @Value ( "${spring.mail.username}" )
    private String mailUsername;
    @Value ( "${mail.to}" )
    private String mailToSend;

    public void sendEmail( String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom ( mailUsername );
        simpleMailMessage.setTo( mailToSend );
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }
}

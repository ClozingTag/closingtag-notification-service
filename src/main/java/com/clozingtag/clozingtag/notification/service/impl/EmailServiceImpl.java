package com.clozingtag.clozingtag.notification.service.impl;

import com.clozingtag.clozingtag.notification.service.configuration.AppConfiguration;
import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import com.clozingtag.clozingtag.notification.service.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final AppConfiguration appConfiguration;

    public EmailServiceImpl(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }


    @Override
    public void sendEmail(NotificationRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getEmail());
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getBody());
//        mailMessage.setFrom(appConfiguration.getMTrapConfig().getFrom());
        try {
            getJavaMailSender().send(mailMessage);
        } catch (MailException ex) {
            log.info("email error {}", ex);
        }

    }
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//        mailSenderImpl.setHost(appConfiguration.getMailtrapConfig().getHost());
//        mailSenderImpl.setPort(appConfiguration.getMailtrapConfig().getPort());
//        mailSenderImpl.setUsername(appConfiguration.getMailtrapConfig().getUsername());
//        mailSenderImpl.setPassword(appConfiguration.getMailtrapConfig().getPassword());
//        mailSenderImpl.setProtocol(appConfiguration.getMailtrapConfig().getProtocol());
        return mailSenderImpl;
    }

}

package com.clozingtag.clozingtag.notification.service.service;


import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

  void sendEmail(NotificationRequest request);
}

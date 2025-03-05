package com.clozingtag.clozingtag.notification.service.service;


import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import com.clozingtag.clozingtag.notification.service.dto.NotificationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

  void createNotification(NotificationRequest NotificationRequest);

  void deleteNotification(long id);

  NotificationResponse getNotificationId(Long Id);

  List<NotificationResponse> getNotifications(Pageable pageable);
}

package com.clozingtag.clozingtag.notification.service.impl;


import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import com.clozingtag.clozingtag.notification.service.dto.NotificationResponse;
import com.clozingtag.clozingtag.notification.service.entity.NotificationEntity;
import com.clozingtag.clozingtag.notification.service.mapper.NotificationMapper;
import com.clozingtag.clozingtag.notification.service.repository.NotificationRepository;
import com.clozingtag.clozingtag.notification.service.service.EmailService;
import com.clozingtag.clozingtag.notification.service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository, EmailService emailService, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void createNotification(NotificationRequest request) {
        log.info("create new notification: {}", request);
        NotificationEntity notificationEntity = notificationMapper.createNotificationEntityFromRequest(request);
        NotificationEntity savedNotificationEntity = notificationRepository.save(notificationEntity);
        log.info("saved notification {}", savedNotificationEntity);
        emailService.sendEmail(request);
    }

    @Override
    public void deleteNotification(long id) {
        NotificationEntity notificationEntity = notificationRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Notification with Id {} not found", id);
                    return new IllegalArgumentException("Notification not found with id: " + id);
                });
        notificationRepository.delete(notificationEntity);
    }

    @Override
    public NotificationResponse getNotificationId(Long Id) {
        log.info("Fetching notification with ID: {}", Id);
        return notificationRepository.findById(Id)
                .map(this::buildNotificationResponse)
                .orElseThrow(() -> {
                    log.warn("Notification with ID {} not found", Id);
                    return new IllegalArgumentException("Notification not found with id: " + Id);
                });
    }

    @Override
    public List<NotificationResponse> getNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable).getContent()
                .stream().map(this::buildNotificationResponse).toList();
    }

    private NotificationResponse buildNotificationResponse(NotificationEntity notificationEntity) {
        return notificationMapper.createNotificationResponseFromEntity(notificationEntity);
    }

}

package com.clozingtag.clozingtag.notification.service.mapper;



import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import com.clozingtag.clozingtag.notification.service.dto.NotificationResponse;
import com.clozingtag.clozingtag.notification.service.entity.NotificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationEntity createNotificationEntityFromRequest(NotificationRequest request);

    NotificationResponse createNotificationResponseFromEntity(NotificationEntity notification);
}
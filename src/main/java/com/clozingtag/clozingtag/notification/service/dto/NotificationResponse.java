package com.clozingtag.clozingtag.notification.service.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse{

    private Long id;

    private String userId;

    private String name;

    private String email;

    private String subject;

    private String body;

    private LocalDateTime createdAt;
}

package com.clozingtag.clozingtag.notification.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class NotificationRequest {
    private Long userId;
    private String name;
    private String email;
    private String subject;
    private String body;
    private String template;
    private Map<String, Object> emailParams;
}
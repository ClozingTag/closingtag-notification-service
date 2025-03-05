package com.clozingtag.clozingtag.notification.service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;


@Table
@Entity(name = "ct_notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationEntity  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String name;

  private String email;

  private String subject;

  private String body;

  @CreationTimestamp
  private LocalDateTime createdAt;
}

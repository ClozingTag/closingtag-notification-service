package com.clozingtag.clozingtag.notification.service.repository;

import com.clozingtag.clozingtag.notification.service.entity.NotificationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}

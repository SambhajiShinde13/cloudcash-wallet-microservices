package com.CloudCash.notification_service.repository;

import com.CloudCash.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRespository  extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(String userId);
}

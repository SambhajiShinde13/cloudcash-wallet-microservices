package com.CloudCash.notification_service.service;

import com.CloudCash.notification_service.entity.Notification;
import com.CloudCash.notification_service.repository.NotificationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{


    public NotificationServiceImpl(NotificationRespository notificationRespository) {
        this.notificationRespository = notificationRespository;
    }

    private NotificationRespository notificationRespository;

    @Override
    public Notification sendNotification(Notification notification) {
         notification.setSentAt(LocalDateTime.now());
         return notificationRespository.save(notification);
    }

    @Override
    public List<Notification> getNotificationByUserId(String userId) {
        return notificationRespository.findByUserId(userId);

    }
}

package com.CloudCash.notification_service.service;

import com.CloudCash.notification_service.entity.Notification;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.nodes.NodeTuple;

import java.util.List;

@Configuration
public interface NotificationService {
    Notification sendNotification(Notification notification);


    List<Notification> getNotificationByUserId(String userId);
}

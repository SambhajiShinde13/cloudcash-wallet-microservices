package com.CloudCash.notification_service.controller;

import com.CloudCash.notification_service.entity.Notification;
import com.CloudCash.notification_service.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    private NotificationService notificationService;

    @PostMapping
    public Notification sendNotification(@RequestBody Notification notification){
        return notificationService.sendNotification(notification);

    }

    @GetMapping("/{userId}")
    public List<Notification> getNotificationByUser(@PathVariable String userId){
            return notificationService.getNotificationByUserId(userId);
        }


}

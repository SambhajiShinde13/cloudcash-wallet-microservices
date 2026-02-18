package com.CloudCash.notification_service.kafka;

import com.CloudCash.notification_service.entity.Notification;
import com.CloudCash.notification_service.entity.Transaction;
import com.CloudCash.notification_service.repository.NotificationRespository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationConsumer {
    private final NotificationRespository notificationRespository;
    private final ObjectMapper mapper;

    public NotificationConsumer(NotificationRespository notificationRespository,ObjectMapper mapper) {
        this.notificationRespository = notificationRespository;
        this.mapper = mapper;

         this.mapper.registerModule(new JavaTimeModule());
         this.mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }

    @KafkaListener(topics = "txn-initiated", groupId = "notification-group")
        public void listener(String message) throws JsonProcessingException {
        Transaction txn = mapper.readValue(message,Transaction.class);

        Notification notification = new Notification();
        String receiverUserId = String.valueOf(txn.getReceiverId());
        String senderId = String.valueOf(txn.getSenderId());

        String notify = "$ "+txn.getAmount() + "recieve from " +txn.getSenderId();

        notification.setMessage(notify);
        LocalDateTime now = LocalDateTime.now();
        notification.setSentAt(now);

        System.out.println("Notification saving ............!"+notification);
        notificationRespository.save(notification);

        }




}

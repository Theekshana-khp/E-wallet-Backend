package com.example.notification_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationDto {
    private Long userId;

    private String notificationType;

    private String title;

    private String message;

    private Boolean isRead;

    private String status;

    private LocalDateTime createdAt;
}


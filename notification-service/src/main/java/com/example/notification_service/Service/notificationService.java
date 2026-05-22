package com.example.notification_service.Service;

import com.example.notification_service.Dto.NotificationDto;
import com.example.notification_service.Entity.notificationEntity;
import com.example.notification_service.Repositery.notificationRepositery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class notificationService {
    private notificationRepositery notificationRepo;

    public notificationService(notificationRepositery notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public Boolean sendNotification(Long userId, String notificationType, String title, String message) {
        if (userId == null || userId <= 0) {
            return false;
        }

        notificationEntity notification = new notificationEntity();
        notification.setUserId(userId);
        notification.setNotificationType(notificationType);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setIsRead(false);
        notification.setStatus("SENT");

        return notificationRepo.save(notification).getId() != null;
    }

    public List<NotificationDto> getUserNotifications(Long userId) {
        List<notificationEntity> notifications = notificationRepo.findByUserId(userId);
        return notifications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> getUnreadNotifications(Long userId) {
        List<notificationEntity> notifications = notificationRepo.findByUserIdAndIsRead(userId, false);
        return notifications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public long getUnreadNotificationCount(Long userId) {
        return notificationRepo.countByUserIdAndIsRead(userId, false);
    }

    public Boolean markAsRead(Long notificationId) {
        try {
            notificationEntity notification = notificationRepo.findById(notificationId)
                    .orElse(null);

            if (notification == null) {
                return false;
            }

            notification.setIsRead(true);
            notificationRepo.save(notification);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteNotification(Long notificationId) {
        try {
            notificationRepo.deleteById(notificationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private NotificationDto mapToDto(notificationEntity entity) {
        return new NotificationDto(
                entity.getUserId(),
                entity.getNotificationType(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getIsRead(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}


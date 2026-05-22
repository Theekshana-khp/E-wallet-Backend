package com.example.notification_service.Controller;

import com.example.notification_service.Dto.NotificationDto;
import com.example.notification_service.Service.notificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notification")
public class notificationController {
    private final notificationService notificationServices;

    public notificationController(notificationService notificationService) {
        this.notificationServices = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendNotification(
            @RequestParam Long userId,
            @RequestParam String notificationType,
            @RequestParam String title,
            @RequestParam String message) {

        Map<String, String> response = new HashMap<>();

        if (userId == null || userId <= 0) {
            response.put("error", "Invalid userId");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        boolean sent = notificationServices.sendNotification(userId, notificationType, title, message);

        if (!sent) {
            response.put("error", "Failed to send notification");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("message", "Notification sent successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<NotificationDto> notifications = notificationServices.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationDto>> getUnreadNotifications(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<NotificationDto> notifications = notificationServices.getUnreadNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/{userId}/unread/count")
    public ResponseEntity<Map<String, Long>> getUnreadNotificationCount(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        long count = notificationServices.getUnreadNotificationCount(userId);
        Map<String, Long> response = new HashMap<>();
        response.put("unreadCount", count);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Map<String, String>> markAsRead(@PathVariable Long notificationId) {
        Map<String, String> response = new HashMap<>();

        if (notificationId == null || notificationId <= 0) {
            response.put("error", "Invalid notificationId");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        boolean marked = notificationServices.markAsRead(notificationId);

        if (!marked) {
            response.put("error", "Failed to mark notification as read");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("message", "Notification marked as read");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Map<String, String>> deleteNotification(@PathVariable Long notificationId) {
        Map<String, String> response = new HashMap<>();

        if (notificationId == null || notificationId <= 0) {
            response.put("error", "Invalid notificationId");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        boolean deleted = notificationServices.deleteNotification(notificationId);

        if (!deleted) {
            response.put("error", "Failed to delete notification");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("message", "Notification deleted successfully");
        return ResponseEntity.ok(response);
    }
}


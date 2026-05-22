package com.example.notification_service.Repositery;

import com.example.notification_service.Entity.notificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface notificationRepositery extends JpaRepository<notificationEntity, Long> {
    List<notificationEntity> findByUserId(Long userId);

    List<notificationEntity> findByUserIdAndIsRead(Long userId, Boolean isRead);

    long countByUserIdAndIsRead(Long userId, Boolean isRead);
}


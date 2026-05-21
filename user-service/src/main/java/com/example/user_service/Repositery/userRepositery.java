package com.example.user_service.Repositery;

import com.example.user_service.Entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepositery extends JpaRepository<userEntity, Long> {
    boolean existsByKeycloakId(String keycloakId);
    Optional<userEntity> findByKeycloakId(String keycloakId);
}

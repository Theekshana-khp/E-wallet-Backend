package com.example.user_service.Repositery;

import com.example.user_service.Entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepositery extends JpaRepository<userEntity, Integer> {
    boolean existsByKeycloakId(String keycloakId);
}

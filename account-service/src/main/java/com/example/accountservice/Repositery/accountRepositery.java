package com.example.accountservice.Repositery;

import com.example.accountservice.Entity.accountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface accountRepositery extends JpaRepository<accountEntity, Long> {

    Optional<accountEntity> findByUserId(Long userId);
}

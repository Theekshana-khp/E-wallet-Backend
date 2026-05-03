package com.example.transaction_service.repository;

import com.example.transaction_service.entity.transactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<transactionEntity, Long> {}
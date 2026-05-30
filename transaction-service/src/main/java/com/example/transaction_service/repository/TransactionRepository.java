package com.example.transaction_service.repository;

import com.example.transaction_service.entity.transactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TransactionRepository extends JpaRepository<transactionEntity, Long> {
    Collection<transactionEntity> findAllByFromWalletIdOrToWalletId(Long fromWalletId, Long toWalletId);
}
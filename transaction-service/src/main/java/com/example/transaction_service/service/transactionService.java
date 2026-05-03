package com.example.transaction_service.service;

import com.example.transaction_service.dto.transactionDTO;
import com.example.transaction_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class transactionService {
    private final TransactionRepository repository;

    public transactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<transactionDTO> getAllTransactions() {
        return repository.findAll().stream()
                .map(transaction -> new transactionDTO(
                        transaction.getTransactionId(),
                        transaction.getFromWalletId(),
                        transaction.getToWalletId(),
                        transaction.getAmount(),
                        transaction.getFee(),
                        transaction.getCurrency(),
                        transaction.getType(),
                        transaction.getStatus(),
                        transaction.getDescription(),
                        transaction.getCurrentWalletAmount(),
                        transaction.getCreatedAt()
                ))
                .toList();
    }

}

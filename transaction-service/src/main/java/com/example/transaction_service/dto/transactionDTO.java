package com.example.transaction_service.dto;

import com.example.transaction_service.common.enums.TransactionStatus;
import com.example.transaction_service.common.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class transactionDTO {
    private String transactionId;

    private Long fromWalletId;

    private Long toWalletId;

    private BigDecimal amount;

    private BigDecimal fee;

    private String currency;

    private TransactionType type;

    private TransactionStatus status;

    private String description;

    private BigDecimal currentWalletAmount;

    private LocalDateTime createdAt;

}


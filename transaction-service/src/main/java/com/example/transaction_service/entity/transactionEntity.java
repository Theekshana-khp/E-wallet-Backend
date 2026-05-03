package com.example.transaction_service.entity;

import com.example.transaction_service.common.enums.TransactionStatus;
import com.example.transaction_service.common.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class transactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String transactionId;

    @Column(name = "from_wallet_id" , nullable = false)
    public Long fromWalletId;

    @Column(name = "to_wallet_id")
    private Long toWalletId;

    private BigDecimal amount;

    private BigDecimal fee = BigDecimal.ZERO;

    private String currency = "LKR";

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.PENDING;

    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "current_wallet_amount")
    private BigDecimal currentWalletAmount;

}
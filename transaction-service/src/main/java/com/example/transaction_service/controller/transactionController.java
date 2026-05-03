package com.example.transaction_service.controller;

import com.example.transaction_service.dto.transactionDTO;
import com.example.transaction_service.service.transactionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class transactionController {
    private final transactionService service;

    public transactionController(transactionService transaction) {
        this.service = transaction;
    }

    @GetMapping
    public List<transactionDTO> getTransaction() {
        return service.getAllTransactions();
    }

}

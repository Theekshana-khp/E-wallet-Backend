package com.example.dashboard_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class dashboardDTO {
    public List<transactionDTO> transactions;
}

package com.example.user_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String nic;

    private LocalDate dateOfBirth;

    private String address;

    private String status;

    private LocalDateTime createdAt;
}

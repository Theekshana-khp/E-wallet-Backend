package com.example.accountservice.Service;

import com.example.accountservice.Dto.accountDto;
import com.example.accountservice.Entity.accountEntity;
import com.example.accountservice.Repositery.accountRepositery;
import org.springframework.stereotype.Service;

@Service
public class accountService {
    private accountRepositery accountRepo;

    accountService(accountRepositery accountRepo){
        this.accountRepo=accountRepo;
    }

    public accountDto getAccount(Long userId){
        accountDto returnDetails = new accountDto();

        accountEntity account =accountRepo.findByUserId(userId)
                .orElse(new accountEntity());

        returnDetails.setAccountNumber(account.getAccountNumber());
        returnDetails.setBalance(account.getBalance());
        returnDetails.setOwnerName(account.getOwnerName());
        returnDetails.setCurrency(account.getCurrency());
        returnDetails.setStatus(account.getStatus());
        returnDetails.setCreatedAt(account.getCreatedAt());
        returnDetails.setUpdatedAt(account.getUpdatedAt());

        return returnDetails;
    }
}

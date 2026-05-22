package com.example.accountservice.Controller;

import com.example.accountservice.Dto.accountDto;
import com.example.accountservice.Service.accountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class accountController {
    private final accountService accountService;

    public accountController(accountService accountServices) {
        accountService = accountServices;
    }

    @GetMapping("/getAccountDetails")
    public accountDto getAccountDetials(@RequestParam Long userId) {
            return accountService.getAccount(userId);
    }
}

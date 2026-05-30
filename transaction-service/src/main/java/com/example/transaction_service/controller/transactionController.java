package com.example.transaction_service.controller;

import com.example.transaction_service.dto.accountDto;
import com.example.transaction_service.dto.transactionDTO;
import com.example.transaction_service.service.transactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Collections;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class transactionController {

    private final transactionService service;
    private final WebClient webClient;

    public transactionController(
            transactionService transaction,
            WebClient.Builder builder
    ) {
        this.service = transaction;
        this.webClient = builder.build();
    }

    @GetMapping("/{userID}")
    public Mono<List<transactionDTO>> getTransaction(@PathVariable String userID,
                                                     @AuthenticationPrincipal Jwt jwt) {

        String token = jwt.getTokenValue();

        return webClient.get()
                .uri("http://account-service/account/getAccountDetails?userId={user}", userID)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(accountDto.class)

                .flatMap(accountData -> {

                    if (accountData == null ||
                            accountData.getAccountNumber() == null) {

                        return Mono.just(Collections.emptyList());
                    }

                    return Mono.fromCallable(() ->
                            service.getAllTransactions(
                                    accountData.getAccountNumber()
                            )
                    ).subscribeOn(Schedulers.boundedElastic());
                });
    }
}
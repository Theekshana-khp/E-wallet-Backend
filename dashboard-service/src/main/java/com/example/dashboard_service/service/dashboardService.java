package com.example.dashboard_service.service;

import com.example.dashboard_service.dto.NotificationDto;
import com.example.dashboard_service.dto.accountDto;
import com.example.dashboard_service.dto.dashboardDTO;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import com.example.dashboard_service.dto.transactionDTO;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class dashboardService {

    private final WebClient webClient;
    private KafkaTemplate<String, String> kafkaTemplate;

    public dashboardService(WebClient.Builder builder,
                            KafkaTemplate<String, String> kafkaTemplate) {
        this.webClient = builder.build();
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<dashboardDTO> getDashboard (String keyClockId , String token){
        return webClient.get()
                .uri("http://user-service/user/getId?keycloakId=" + keyClockId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Long.class)
                .flatMap(user -> {
                    Mono<List<transactionDTO>> transactions =
                            webClient.get()
                                    .uri("http://transaction-service/transaction")
                                    .retrieve()
                                    .bodyToFlux(transactionDTO.class)
                                    .collectList();
                    Mono<List<NotificationDto>> notifications =
                            webClient.get()
                                    .uri("http://notification-service/notification/user/{user}", user)
                                    .retrieve()
                                    .bodyToFlux(NotificationDto.class)
                                    .collectList();
                    Mono<accountDto> account =
                            webClient.get()
                                    .uri("http://account-service/account/getAccountDetails?userId={user}",user)
                                    .retrieve()
                                    .bodyToMono(accountDto.class);


                    return Mono.zip(transactions, notifications, account)
                            .map(tuple -> {

                                List<transactionDTO> transactionList = tuple.getT1();
                                List<NotificationDto> notificationList = tuple.getT2();
                                accountDto accountData = tuple.getT3();

                                kafkaTemplate.send(
                                        "transaction-topic",
                                        "Load Transaction Success"
                                );

                                return new dashboardDTO(
                                        transactionList,
                                        notificationList,
                                        accountData
                                );
                            });
                });

    }
}

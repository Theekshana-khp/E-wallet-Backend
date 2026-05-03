package com.example.dashboard_service.service;

import com.example.dashboard_service.dto.dashboardDTO;
import org.springframework.kafka.core.KafkaTemplate;
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

    public Mono<dashboardDTO> getDashboard() {

        Mono<List<transactionDTO>> transactions =
                webClient.get()
                        .uri("http://transaction-service/transaction")
                        .retrieve()
                        .bodyToFlux(transactionDTO.class)
                        .collectList();

        kafkaTemplate.send("transaction-topic", "Load Transaction");
        return transactions
                .map(list -> {
                    kafkaTemplate.send("transaction-topic", "Load Transaction Success");
                    return new dashboardDTO(list);
                });
    }
}

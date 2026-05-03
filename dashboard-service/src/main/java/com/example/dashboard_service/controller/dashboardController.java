package com.example.dashboard_service.controller;

import com.example.dashboard_service.dto.dashboardDTO;
import com.example.dashboard_service.dto.transactionDTO;
import com.example.dashboard_service.service.dashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/dashboard")
public class dashboardController {
    private final dashboardService service;

    public dashboardController(dashboardService dashboardServices){
        this.service = dashboardServices;
    }


    @GetMapping
    public Mono<dashboardDTO> getDashboardData(){
        return service.getDashboard();
    }

}

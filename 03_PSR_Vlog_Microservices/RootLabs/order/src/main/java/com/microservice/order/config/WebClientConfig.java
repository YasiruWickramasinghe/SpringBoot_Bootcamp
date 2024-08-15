package com.microservice.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// create web service for inter communication with other applications like inventory or product
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    // Inventory WebClient
    @Bean
    public WebClient inventoryWebClient(){
        return WebClient.builder().baseUrl("http://localhost:8080/api/v1").build();
    }

    // Product WebClient
    @Bean
    public WebClient productWebClient(){
        return WebClient.builder().baseUrl("http://localhost:8082/api/v1").build();
    }
}

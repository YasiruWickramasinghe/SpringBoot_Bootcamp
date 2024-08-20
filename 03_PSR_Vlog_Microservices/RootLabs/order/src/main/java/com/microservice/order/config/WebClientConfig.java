package com.microservice.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// create web service for inter communication with other applications like inventory or product
@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    // Set Service Discovery With Load Balancer in Netflix Eureka

    // Inventory WebClient
    @Bean
    public WebClient inventoryWebClient(){
        return webClientBuilder().baseUrl("http://inventory/api/v1").build();
    }

    // Product WebClient
    @Bean
    public WebClient productWebClient(){
        return webClientBuilder().baseUrl("http://product/api/v1").build();
    }
}

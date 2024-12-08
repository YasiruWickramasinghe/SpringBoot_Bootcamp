package com.pearl.userManageApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//This Class use for CORS Policy Configuration

public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Allow requests from Any
                        //.allowedOrigins("http://localhost:3000") // Allow requests from React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");// Allow HTTP methods
                        //.allowedHeaders("*") // Allow all headers
                        //.allowCredentials(true); // Allow cookies and authentication headers
            }
        };
    }
}

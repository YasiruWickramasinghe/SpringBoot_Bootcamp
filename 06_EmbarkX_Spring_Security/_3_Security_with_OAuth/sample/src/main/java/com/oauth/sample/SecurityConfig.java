package com.oauth.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //Default Security Config
    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest.anyRequest().authenticated())
                //.formLogin(form -> form.defaultSuccessUrl("/hello", true))
                .oauth2Login(oauth2 ->
                        oauth2.defaultSuccessUrl("http://localhost:3000/dashboard", true));

        return http.build();
    }
}

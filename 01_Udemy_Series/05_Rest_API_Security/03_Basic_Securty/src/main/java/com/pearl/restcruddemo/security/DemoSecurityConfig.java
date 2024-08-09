package com.pearl.restcruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails muthu = User.builder()
                .username("muthu")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails pinki = User.builder()
                .username("pinki")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails sanju = User.builder()
                .username("sanju")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return  new InMemoryUserDetailsManager(muthu, pinki, sanju);
    }
}

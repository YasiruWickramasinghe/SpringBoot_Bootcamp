package com.pearl.springcoredemo.config;

import com.pearl.springcoredemo.common.Coach;
import com.pearl.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic") //aquatic is bean id which we given. this can use within @Qualifier annotation or we can use normal @Bean
    public Coach swimCoach(){ //first letter is lowercase
        return new SwimCoach();
    }
}

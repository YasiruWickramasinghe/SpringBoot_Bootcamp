package com.rootlabs.Springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //get value from application.properties file
    @Value("${welcome.message}")
    private String WelcomeMessage;

    @GetMapping("/")
    public String helloWorld(){
        return WelcomeMessage;
    }
}

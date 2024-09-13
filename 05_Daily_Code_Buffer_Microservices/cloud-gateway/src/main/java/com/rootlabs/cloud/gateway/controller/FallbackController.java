package com.rootlabs.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/users")
    public String userServiceFallback() {
        return "User service is currently unavailable. Please try again later.";
    }

    @GetMapping("/departments")
    public String departmentServiceFallback() {
        return "Department service is currently unavailable. Please try again later.";
    }
}

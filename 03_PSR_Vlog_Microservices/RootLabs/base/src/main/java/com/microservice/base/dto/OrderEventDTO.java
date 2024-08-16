package com.microservice.base.dto;
//for asynchronous communication - kafka
//for consumer

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDTO {
    private String message;
    private String status;
}


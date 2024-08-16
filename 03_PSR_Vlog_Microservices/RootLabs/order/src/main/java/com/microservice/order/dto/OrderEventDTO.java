//for asynchronous communication - kafka
//for producer
package com.microservice.order.dto;

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

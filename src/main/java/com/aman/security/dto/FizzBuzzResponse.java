package com.aman.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FizzBuzzResponse {
    private String message;
    private String statusCode;
}

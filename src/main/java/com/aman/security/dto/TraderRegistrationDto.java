package com.aman.security.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TraderRegistrationDto {
    private String name;
    private String email;
    private BigDecimal balance;
}

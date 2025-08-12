package com.aman.security.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TraderAddMoneyDto {
    private String email;
    private BigDecimal amount;
}

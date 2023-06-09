package com.br.internetbankingservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionResponseDTO {

    private String transactionType;
    private BigDecimal amount;
    private Double fee;
    private BigDecimal balance;

}

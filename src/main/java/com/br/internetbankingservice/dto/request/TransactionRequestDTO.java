package com.br.internetbankingservice.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {

    private BigDecimal amount;
    private String accountNumber;

}

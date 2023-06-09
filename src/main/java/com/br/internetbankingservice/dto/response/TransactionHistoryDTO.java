package com.br.internetbankingservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionHistoryDTO {

    private String transactionType;
    private BigDecimal amount;
    private LocalDate date;
    private String accountNumber;

}

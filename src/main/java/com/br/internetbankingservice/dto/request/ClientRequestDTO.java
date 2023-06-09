package com.br.internetbankingservice.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ClientRequestDTO {

    private String name;
    private boolean isExclusive;
    private String accountNumber;
    private Date birthDate;
    private BigDecimal balance;

}

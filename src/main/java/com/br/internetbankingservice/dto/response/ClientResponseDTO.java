package com.br.internetbankingservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ClientResponseDTO {

    private String name;
    private boolean isExclusive;
    private BigDecimal balance;
    private String accountNumber;
    private Date birthDate;

}

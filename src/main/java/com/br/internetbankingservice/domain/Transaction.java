package com.br.internetbankingservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(generator = "transaction_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
    private Long id;
    private String transactionType;
    private BigDecimal amount;
    private LocalDate date;
    @ManyToOne
    private Client client;

}

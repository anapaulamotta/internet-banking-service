package com.br.internetbankingservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "client_banking")
public class Client {

    @Id
    @GeneratedValue(generator = "client_banking_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "client_banking_seq", sequenceName = "client_banking_seq", allocationSize = 1)
    private Long id;
    private String name;
    private boolean isExclusive;
    private BigDecimal balance;
    private String accountNumber;
    private Date birthDate;
    @OneToMany
    private List<Transaction> transactions;

}

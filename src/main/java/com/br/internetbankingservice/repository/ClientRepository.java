package com.br.internetbankingservice.repository;

import com.br.internetbankingservice.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> getByAccountNumber(String accountNumber);

}

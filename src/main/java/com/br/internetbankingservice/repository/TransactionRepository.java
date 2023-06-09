package com.br.internetbankingservice.repository;

import com.br.internetbankingservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction where date\\:\\:date = :date", nativeQuery = true)
    List<Transaction> findByDate(@Param("date") String date);

}

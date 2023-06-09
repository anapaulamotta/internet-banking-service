package com.br.internetbankingservice.util;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.domain.Transaction;
import com.br.internetbankingservice.dto.request.ClientRequestDTO;
import com.br.internetbankingservice.dto.response.ClientResponseDTO;
import com.br.internetbankingservice.dto.response.TransactionHistoryDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class MockUtils {
    
    public static Client mockClient(){
        
        Client client = new Client();

        client.setId(1L);
        client.setName("John Doe");
        client.setExclusive(true);
        client.setBalance(BigDecimal.valueOf(1000));
        client.setAccountNumber("123456789");
        client.setBirthDate(new Date());

        return client;

    }

    public static ClientResponseDTO mockClientResponseDTO() {

        ClientResponseDTO client = new ClientResponseDTO();

        client.setName("John Doe");
        client.setExclusive(true);
        client.setBalance(BigDecimal.valueOf(1000));
        client.setAccountNumber("123456789");
        client.setBirthDate(new Date());

        return client;
    }

    public static ClientRequestDTO mockClientRequestDTO(){

        ClientRequestDTO client = new ClientRequestDTO();

        client.setName("John Doe");
        client.setExclusive(true);
        client.setBalance(BigDecimal.valueOf(1000));
        client.setAccountNumber("123456789");
        client.setBirthDate(new Date());

        return client;

    }


        public static Transaction mockTransaction(){

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionType("deposit");
        transaction.setAmount(BigDecimal.valueOf(100.0));
        transaction.setDate(LocalDate.now());
        transaction.setClient(mockClient());

        return transaction;

    }

    public static TransactionHistoryDTO mockTransactionHistoryDTO(){

        TransactionHistoryDTO transactionDTO = new TransactionHistoryDTO();
        transactionDTO.setTransactionType("withdraw");
        transactionDTO.setAmount(BigDecimal.valueOf(1000));
        transactionDTO.setDate(LocalDate.now());
        transactionDTO.setAccountNumber("123456789");

        return transactionDTO;
    }

    
}

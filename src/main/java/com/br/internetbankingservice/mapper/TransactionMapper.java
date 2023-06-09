package com.br.internetbankingservice.mapper;

import com.br.internetbankingservice.domain.Transaction;
import com.br.internetbankingservice.dto.response.TransactionHistoryDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {

    public TransactionHistoryDTO toHistory(Transaction transaction){

        TransactionHistoryDTO historyDTO = new TransactionHistoryDTO();
        historyDTO.setDate(transaction.getDate());
        historyDTO.setAmount(transaction.getAmount());
        historyDTO.setTransactionType(transaction.getTransactionType());
        historyDTO.setAccountNumber(transaction.getClient().getAccountNumber());

        return historyDTO;

    }

    public List<TransactionHistoryDTO> toHistories(List<Transaction> transactions){

        List<TransactionHistoryDTO> histories = new ArrayList<>();

        for (Transaction transaction:transactions){
            histories.add(toHistory(transaction));
        }

        return histories;

    }

}

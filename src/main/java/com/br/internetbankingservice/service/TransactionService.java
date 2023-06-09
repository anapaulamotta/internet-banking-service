package com.br.internetbankingservice.service;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.domain.Transaction;
import com.br.internetbankingservice.dto.response.TransactionHistoryDTO;
import com.br.internetbankingservice.dto.response.TransactionResponseDTO;
import com.br.internetbankingservice.mapper.TransactionMapper;
import com.br.internetbankingservice.repository.ClientRepository;
import com.br.internetbankingservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionRepository repository;

    @Autowired
    TransactionMapper mapper;

    public TransactionResponseDTO withdraw(String accountNumber, BigDecimal amount) {

        Optional<Client> optionalClient = clientRepository.getByAccountNumber(accountNumber);
        BigDecimal withdrawWithFee;
        Double fee = 0.0;


        if (optionalClient.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        if (optionalClient.get().isExclusive()){
            withdrawWithFee = withdrawValueWithFee(amount, fee);
        }else if (amount.compareTo(BigDecimal.valueOf(100)) < 0 || amount.compareTo(BigDecimal.valueOf(100)) == 0){
            withdrawWithFee = withdrawValueWithFee(amount, fee);
        } else if (amount.compareTo(BigDecimal.valueOf(300)) < 0 || amount.compareTo(BigDecimal.valueOf(300)) == 0) {
            fee = 0.004;
            withdrawWithFee = withdrawValueWithFee(amount, fee);
        }else {
            fee = 0.01;
            withdrawWithFee = withdrawValueWithFee(amount, fee);
        }

        BigDecimal newBalance = optionalClient.get().getBalance().subtract(withdrawWithFee);
        Client client = optionalClient.get();
        client.setBalance(newBalance);
        clientRepository.save(client);

        TransactionResponseDTO responseDTO = insertTransaction(withdrawWithFee,"withdraw",client);

        responseDTO.setBalance(client.getBalance());
        responseDTO.setFee(fee);

        return responseDTO;

    }

    public BigDecimal withdrawValueWithFee(BigDecimal amount, Double withdrawalFee){

        BigDecimal fee = BigDecimal.valueOf(1).add(BigDecimal.valueOf(withdrawalFee));

        return amount.multiply(fee);

    }

    public TransactionResponseDTO deposit(String accountNumber, BigDecimal amount) {

        Optional<Client> optionalClient = clientRepository.getByAccountNumber(accountNumber);

        if (optionalClient.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        BigDecimal newBalance = optionalClient.get().getBalance().add(amount);

        Client client = optionalClient.get();

        client.setBalance(newBalance);

        clientRepository.save(client);

        TransactionResponseDTO responseDTO = insertTransaction(amount,"deposit",client);

        responseDTO.setBalance(client.getBalance());

        return responseDTO;

    }

    public TransactionResponseDTO insertTransaction(BigDecimal amount, String transactionType, Client client){

        Transaction transaction = new Transaction();

        transaction.setClient(client);
        transaction.setDate(LocalDate.now());
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);

        repository.save(transaction);

        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setTransactionType(transactionType);
        responseDTO.setAmount(amount);

        return responseDTO;
    }

    public List<TransactionHistoryDTO> getTransactionsByDate(LocalDate date) {

        List<Transaction> transactions = repository.findByDate(date.toString());

        return mapper.toHistories(transactions);

    }
}

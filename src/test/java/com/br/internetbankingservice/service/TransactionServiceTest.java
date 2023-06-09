package com.br.internetbankingservice.service;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.domain.Transaction;
import com.br.internetbankingservice.dto.response.TransactionHistoryDTO;
import com.br.internetbankingservice.dto.response.TransactionResponseDTO;
import com.br.internetbankingservice.mapper.TransactionMapper;
import com.br.internetbankingservice.repository.ClientRepository;
import com.br.internetbankingservice.repository.TransactionRepository;
import com.br.internetbankingservice.util.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    ClientRepository clientRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    TransactionMapper mapper;

    @InjectMocks
    TransactionService transactionService;


    @Test
    void whenGetTransactionsByDate_thenNoTransactionsFound() {
        LocalDate date = LocalDate.now();
        when(transactionRepository.findByDate(date.toString())).thenReturn(null);

        List<TransactionHistoryDTO> transactionHistoryDTOList = transactionService.getTransactionsByDate(date);

        assertEquals(0, transactionHistoryDTOList.size());
        verify(transactionRepository, times(1)).findByDate(date.toString());
    }

    @Test
    void whenGetTransactionsByDate_thenSuccess() {
        LocalDate date = LocalDate.now();
        when(transactionRepository.findByDate(date.toString())).thenReturn(List.of(MockUtils.mockTransaction()));
        when(mapper.toHistories(any())).thenReturn(List.of(MockUtils.mockTransactionHistoryDTO()));

        transactionService.getTransactionsByDate(date);

        verify(transactionRepository, times(1)).findByDate(date.toString());
    }

    @Test
    void whenInsertTransaction_thenSuccess() {
        Client client = MockUtils.mockClient();
        Transaction transaction = MockUtils.mockTransaction();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        transactionService.insertTransaction(BigDecimal.valueOf(500), "withdraw", client);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void whenDeposit_thenClientNotFound() {
        String accountNumber = "123456789";
        BigDecimal value = BigDecimal.valueOf(100);

        when(clientRepository.getByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(accountNumber, value);
        });

        verify(clientRepository, times(1)).getByAccountNumber(accountNumber);
        verifyNoMoreInteractions(clientRepository);
        verifyNoInteractions(transactionRepository);
    }

    @Test
    void whenDeposit_thenSuccess() {
        Client client = MockUtils.mockClient();
        Optional<Client> optionalClient = Optional.of(client);
        when(clientRepository.getByAccountNumber(any())).thenReturn(optionalClient);

        transactionService.deposit("12345", BigDecimal.valueOf(1000));

        verify(clientRepository, times(1)).save(client);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

}
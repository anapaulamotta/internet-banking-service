package com.br.internetbankingservice.controller;

import com.br.internetbankingservice.dto.request.TransactionRequestDTO;
import com.br.internetbankingservice.dto.response.TransactionHistoryDTO;
import com.br.internetbankingservice.dto.response.TransactionResponseDTO;
import com.br.internetbankingservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PutMapping("/{transactionType}")
    public TransactionResponseDTO updateBalance(@PathVariable String transactionType, @RequestBody TransactionRequestDTO requestDTO){

        if (transactionType.equals("withdraw")) {
            return service.withdraw(requestDTO.getAccountNumber(), requestDTO.getAmount());
        } else if (transactionType.equals("deposit")) {
            return service.deposit(requestDTO.getAccountNumber(), requestDTO.getAmount());
        } else {
            throw new IllegalArgumentException("transactionType inválido, insira 'withdraw' ou 'deposit'");
        }
    }

    @GetMapping
    public ResponseEntity<?> getTransactionsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        try {
            List<TransactionHistoryDTO> clients = service.getTransactionsByDate(date);
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }


}

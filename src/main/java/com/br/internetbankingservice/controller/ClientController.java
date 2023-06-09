package com.br.internetbankingservice.controller;

import com.br.internetbankingservice.dto.request.ClientRequestDTO;
import com.br.internetbankingservice.dto.response.ClientResponseDTO;
import com.br.internetbankingservice.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Api
public class ClientController {

    @Autowired
    ClientService service;

    @PostMapping
    public ResponseEntity<String> insertClient(@RequestBody ClientRequestDTO request){

        try {
            service.insertClient(request);
            return ResponseEntity.ok("Cliente inserido com sucesso.");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Erro ao inserir cliente");
        }

    }

    @GetMapping
    public ResponseEntity<?> findClient() {
        try {
            List<ClientResponseDTO> clients = service.findClient();
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

}

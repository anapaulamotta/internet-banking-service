package com.br.internetbankingservice.service;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.dto.request.ClientRequestDTO;
import com.br.internetbankingservice.dto.response.ClientResponseDTO;
import com.br.internetbankingservice.mapper.ClientMapper;
import com.br.internetbankingservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Autowired
    ClientMapper mapper;

    public void insertClient(ClientRequestDTO request) {

        Optional<Client> existentClient = repository.getByAccountNumber(request.getAccountNumber());

        if (existentClient.isPresent())
            throw new IllegalArgumentException("AccountNumber já cadastrado");

        repository.save(mapper.toEntity(request));
    }

    public List<ClientResponseDTO> findClient() {

        List<Client> clients = repository.findAll();

        return mapper.toResponseDTOList(clients);

    }
}

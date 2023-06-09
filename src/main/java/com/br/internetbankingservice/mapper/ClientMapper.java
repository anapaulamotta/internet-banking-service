package com.br.internetbankingservice.mapper;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.dto.request.ClientRequestDTO;
import com.br.internetbankingservice.dto.response.ClientResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO requestDTO){
        Client client = new Client();

        if (nonNull(requestDTO)){
            client.setName(requestDTO.getName());
            client.setExclusive(requestDTO.isExclusive());
            client.setBirthDate(requestDTO.getBirthDate());
            client.setAccountNumber(requestDTO.getAccountNumber());
            client.setBalance(requestDTO.getBalance());
        }

        return client;
    }

    public ClientResponseDTO toResponseDTO(Client client){

        ClientResponseDTO responseDTO = new ClientResponseDTO();

        if (nonNull(client)){
            responseDTO.setName(client.getName());
            responseDTO.setExclusive(client.isExclusive());
            responseDTO.setBirthDate(client.getBirthDate());
            responseDTO.setAccountNumber(client.getAccountNumber());
            responseDTO.setBalance(client.getBalance());
        }

        return responseDTO;

    }

    public List<ClientResponseDTO> toResponseDTOList(List<Client> clients){

        List<ClientResponseDTO> responseDTOS = new ArrayList<>();

        for (Client client:clients){
            responseDTOS.add(toResponseDTO(client));
        }

        return responseDTOS;

    }

}

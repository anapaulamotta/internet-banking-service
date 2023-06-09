package com.br.internetbankingservice.service;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.dto.response.ClientResponseDTO;
import com.br.internetbankingservice.mapper.ClientMapper;
import com.br.internetbankingservice.repository.ClientRepository;
import com.br.internetbankingservice.util.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientMapper clientMapper;

    @InjectMocks
    ClientService clientService;


    @Test
    void whenFindClient_thenReturnEmptyList() {
        List<Client> clients = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientMapper.toResponseDTOList(clients)).thenReturn(new ArrayList<>());

        List<ClientResponseDTO> responseDTOList = clientService.findClient();

        assertEquals(0, responseDTOList.size());
        verify(clientRepository, times(1)).findAll();
        verify(clientMapper, times(1)).toResponseDTOList(clients);
    }

    @Test
    void whenFindClient_thenSuccess() {
        List<Client> clients = List.of(MockUtils.mockClient());
        List<ClientResponseDTO> responseDTOs = List.of(MockUtils.mockClientResponseDTO());

        when(clientRepository.findAll()).thenReturn(clients);

        when(clientMapper.toResponseDTOList(clients)).thenReturn(responseDTOs);

        clientService.findClient();

        verify(clientRepository, times(1)).findAll();
        verify(clientMapper, times(1)).toResponseDTOList(clients);
    }

}
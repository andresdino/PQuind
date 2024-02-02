package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Repository.ClienteRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach()
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCliente() {
        ClienteDTO clienteDTO = ClienteDTO
                .builder()
                .fechaNacimiento("2000-09-12")
                .build();

        ClienteEntity cliente = ClienteEntity
                .builder()
                .id(1L)
                .build();
        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteEntity result = clienteService.createCliente(clienteDTO);

    }

    @Test
    void updateCliente() {
    }

    @Test
    void deleteCliente() {
    }

    @Test
    void getAllClientes() {
    }

    @Test
    void getClienteById() {
    }
}
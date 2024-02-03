package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.TipoDocumento;
import com.example.pruebatecnicaquind.Repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCliente() {
        ClienteDto clienteDto = ClienteDto.builder()
                .fechaNacimiento("2000-11-11")
                .build();

        ClienteEntity clienteEntity = new ClienteEntity();
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        clienteService.createCliente(clienteDto);
    }

    @Test
    void createClienteMenor() {
        ClienteDto clienteDto = ClienteDto.builder()
                .fechaNacimiento("2023-11-11")
                .build();

        clienteService.createCliente(clienteDto);
    }

    @Test
    void updateCliente() {
        ClienteDto clienteDto = ClienteDto.builder()
                .numeroIdentificacion("123456789")
                .fechaNacimiento("1990-01-01")
                .build();
        ClienteEntity clienteEntity = new ClienteEntity();
        Optional<ClienteEntity> existingClienteEntityOptional = Optional.of(clienteEntity);

        ClienteRepository clienteRepositoryMock = mock(ClienteRepository.class);
        when(clienteRepositoryMock.findClienteEntityByNumeroIdentificacion(clienteDto.getNumeroIdentificacion())).thenReturn(existingClienteEntityOptional);
        when(clienteRepositoryMock.save(any(ClienteEntity.class))).thenReturn(clienteEntity);

        clienteService.updateCliente(clienteDto.getNumeroIdentificacion(), clienteDto);
    }

    @Test
    void updateClienteMenor() {
        ClienteDto clienteDto = ClienteDto.builder()
                .numeroIdentificacion("123456789")
                .fechaNacimiento("2010-01-01")
                .build();
        ClienteEntity existingClienteEntity = new ClienteEntity();
        Optional<ClienteEntity> existingClienteEntityOptional = Optional.of(existingClienteEntity);

        clienteRepository = mock(ClienteRepository.class);
        when(clienteRepository.findClienteEntityByNumeroIdentificacion(clienteDto.getNumeroIdentificacion())).thenReturn(existingClienteEntityOptional);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(existingClienteEntity);

        clienteService.updateCliente(clienteDto.getNumeroIdentificacion(), clienteDto);
    }

    @Test
    void updateClienteNoExistente() {
        String numeroIdentificacion = "clienteExistente";
        ClienteDto clienteDto = ClienteDto.builder()
                .tipoIdentificacion(TipoDocumento.CC)
                .numeroIdentificacion(numeroIdentificacion)
                .nombre("Nombre")
                .apellido("Apellido")
                .email("correo@ejemplo.com")
                .fechaNacimiento("1990-01-01")
                .build();

        ClienteEntity clienteEntityExistente = new ClienteEntity();
        clienteEntityExistente.setId(1L);
        Optional<ClienteEntity> existingClienteEntityOptional = Optional.of(clienteEntityExistente);

        when(clienteRepository.findClienteEntityByNumeroIdentificacion(numeroIdentificacion)).thenReturn(existingClienteEntityOptional);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntityExistente);

        clienteService.updateCliente(numeroIdentificacion, clienteDto);

    }

    @Test
    void deleteCliente() {
        ClienteDto clienteDto = ClienteDto.builder()
                .numeroIdentificacion("123456789")
                .build();

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setProductoEntity(new ArrayList<>());
        Optional<ClienteEntity> clienteEntityOptional = Optional.of(clienteEntity);

        when(clienteRepository.findClienteEntityByNumeroIdentificacion(clienteDto.getNumeroIdentificacion())).thenReturn(clienteEntityOptional);
        when(clienteRepository.existsById(clienteEntity.getId())).thenReturn(true);

        clienteService.deleteCliente(clienteDto.getNumeroIdentificacion());
    }

    @Test
    void deleteClienteConProductosAsociados() {
        ClienteDto clienteDto = ClienteDto.builder()
                .numeroIdentificacion("123456789")
                .build();

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        List<ProductoEntity> productos = new ArrayList<>();
        productos.add(new ProductoEntity());
        clienteEntity.setProductoEntity(productos);
        Optional<ClienteEntity> clienteEntityOptional = Optional.of(clienteEntity);

        when(clienteRepository.findClienteEntityByNumeroIdentificacion(clienteDto.getNumeroIdentificacion())).thenReturn(clienteEntityOptional);
        when(clienteRepository.existsById(clienteEntity.getId())).thenReturn(true);

        clienteService.deleteCliente(clienteDto.getNumeroIdentificacion());
    }

    @Test
    void deleteClienteNoEncontrado() {
        ClienteDto clienteDto = ClienteDto.builder()
                .numeroIdentificacion("123456789")
                .build();

        when(clienteRepository.findClienteEntityByNumeroIdentificacion(clienteDto.getNumeroIdentificacion())).thenReturn(Optional.empty());

        clienteService.deleteCliente(clienteDto.getNumeroIdentificacion());
    }
}